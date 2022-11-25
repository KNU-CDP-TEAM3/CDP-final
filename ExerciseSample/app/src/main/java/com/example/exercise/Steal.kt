/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.exercise

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.ambient.AmbientModeSupport.AmbientCallback
import com.google.android.gms.wearable.*
import com.example.exercise.databinding.FragmentExerciseBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import java.nio.charset.StandardCharsets

class Steal : WearableListenerService(),
    DataClient.OnDataChangedListener,
    MessageClient.OnMessageReceivedListener,
    CapabilityClient.OnCapabilityChangedListener {
    private var activityContext: Context? = null
    private val TAG_MESSAGE_RECEIVED = "receive1"
    private val APP_OPEN_WEARABLE_PAYLOAD_PATH = "/APP_OPEN_WEARABLE_PAYLOAD"
    private var mobileDeviceConnected: Boolean = false

    // Payload string items
    private val wearableAppCheckPayloadReturnACK = "AppOpenWearableACK"

    private val MESSAGE_ITEM_RECEIVED_PATH: String = "/message-item-received"


    private var messageEvent: MessageEvent? = null
    private var mobileNodeUri: String? = null



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        activityContext = this
        try {
            Wearable.getDataClient(activityContext!!).addListener(this)
            Wearable.getMessageClient(activityContext!!).addListener(this)
            Wearable.getCapabilityClient(activityContext!!)
                .addListener(this, Uri.parse("wear://"), CapabilityClient.FILTER_REACHABLE)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        var S1 = intent.getStringExtra("heartRate")
        var S2 = intent.getStringExtra("calories")
        var S3 = intent.getStringExtra("distance")
        var S4 = intent.getStringExtra("laps")
        var S5 = intent.getStringExtra("speed")
        var S = S1 + "A" + S2 + "A" + S3 + "A" + S4 + "A" + S5 + "A"

        if (S!!.isNotEmpty()) {
            val nodeId: String = NetWorking().steal
            // Set the data of the message to be the bytes of the Uri.
            val payload: ByteArray =
                S.toByteArray()

            // Send the rpc
            // Instantiates clients without member variables, as clients are inexpensive to
            // create. (They are cached and shared between GoogleApi instances.)
            val sendMessageTask =
                Wearable.getMessageClient(activityContext!!)
                    .sendMessage(nodeId, MESSAGE_ITEM_RECEIVED_PATH, payload)


            sendMessageTask.addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("send1", "Message sent successfully")
                } else {
                    Log.d("send1", "Message failed.")
                }
            }
        } else {
            Toast.makeText(
                activityContext,
                "Message content is empty. Please enter some message and proceed",
                Toast.LENGTH_SHORT
            ).show()
        }


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDataChanged(p0: DataEventBuffer) {
    }

    override fun onCapabilityChanged(p0: CapabilityInfo) {

    }


}
