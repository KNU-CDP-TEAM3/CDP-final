package com.example.exercise

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.exercise.databinding.ActivityMainhomeBinding
import com.example.exercise.ui.calories.CaloriesFragment
import com.example.exercise.ui.exerciserecord.ExerciserecordFragment
import com.example.exercise.ui.home.HomeFragment
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.*
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*
import java.nio.charset.StandardCharsets
import java.util.*

class MainActivity : AppCompatActivity(),CoroutineScope by MainScope(),
    DataClient.OnDataChangedListener,
    MessageClient.OnMessageReceivedListener,
    CapabilityClient.OnCapabilityChangedListener {
    var activityContext: Context? = null
    private val wearableAppCheckPayload = "AppOpenWearable"
    private val wearableAppCheckPayloadReturnACK = "AppOpenWearableACK"
    private var wearableDeviceConnected: Boolean = false

    private var currentAckFromWearForAppOpenCheck: String? = null
    private val APP_OPEN_WEARABLE_PAYLOAD_PATH = "/APP_OPEN_WEARABLE_PAYLOAD"

    private val MESSAGE_ITEM_RECEIVED_PATH: String = "/message-item-received"

    private val TAG_GET_NODES: String = "getnodes1"
    private val TAG_MESSAGE_RECEIVED: String = "receive1"

    private var messageEvent: MessageEvent? = null
    private var wearableNodeUri: String? = null

    private lateinit var homeFragment: HomeFragment
//    private lateinit var dataFragment: DataFragment
    private lateinit var exerciserecordFragment: ExerciserecordFragment
//    private lateinit var mypageFragment: MyPageFragment
    private lateinit var caloriesFragment: CaloriesFragment


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainhomeBinding
    private lateinit var ny : Intent
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainhomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initNavigationBar()

        activityContext = this
        wearableDeviceConnected = false

        if (!wearableDeviceConnected) {
            val tempAct: Activity = activityContext as MainActivity
            //Couroutine
            initialiseDevicePairing(tempAct)
        }

        setSupportActionBar(binding.appBarMainhome.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_mainhome)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_exerciserecord, R.id.nav_calories
        ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.appBarMainhome.heartRateTextView.setOnClickListener {
            ny = Intent(this, HeartGraph::class.java)
            startActivity(ny)
        }
        binding.appBarMainhome.calorieTextView.setOnClickListener {
            ny = Intent(this, CalorieGraph::class.java)
            startActivity(ny)
        }
        binding.appBarMainhome.distanceTextView.setOnClickListener {
            ny = Intent(this, DistanceGraph::class.java)
            startActivity(ny)
        }
        binding.appBarMainhome.lapsTextView.setOnClickListener {
            ny = Intent(this, LapsGraph::class.java)
            startActivity(ny)
        }
        binding.appBarMainhome.speedTextView.setOnClickListener {
            ny = Intent(this, SpeedGraph::class.java)
            startActivity(ny)
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mainhome)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun initNavigationBar(){
        binding.navView1.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.navigation_home -> {
                        homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_mainhome, homeFragment)
                            .commit()
                    }
                    R.id.navigation_exercise -> {
                        exerciserecordFragment = ExerciserecordFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_mainhome, exerciserecordFragment)
                            .commit()
                    }
                    R.id.navigation_diet -> {
                        caloriesFragment = CaloriesFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_mainhome, caloriesFragment)
                            .commit()
                    }
                    /*R.id.navigation_data -> {
                        dataFragment = DataFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_mainhome, dataFragment)
                            .commit()

                    }
                    R.id.navigation_mypage -> {
                        mypageFragment = MyPageFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_mainhome, mypageFragment)
                            .commit()

                    }*/
                }
                true
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun initialiseDevicePairing(tempAct: Activity) {
        //Coroutine
        launch(Dispatchers.Default) {
            var getNodesResBool: BooleanArray? = null

            try {
                getNodesResBool =
                    getNodes(tempAct.applicationContext)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            //UI Thread
            withContext(Dispatchers.Main) {
                if (getNodesResBool!![0]) {
                    //if message Acknowlegement Received
                    if (getNodesResBool[1]) {
                        Toast.makeText(
                            activityContext,
                            "Wearable device paired and app is open. Tap the \"Send Message to Wearable\" button to send the message to your wearable device.",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        Toast.makeText(
                            activityContext,
                            "A wearable device is paired but the wearable app on your watch isn't open. Launch the wearable app and try again.",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                } else {
                    Toast.makeText(
                        activityContext,
                        "No wearable device paired. Pair a wearable device to your phone using the Wear OS app and try again.",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }


    private fun getNodes(context: Context): BooleanArray {
        val nodeResults = HashSet<String>()
        val resBool = BooleanArray(2)
        resBool[0] = false //nodePresent
        resBool[1] = false //wearableReturnAckReceived
        val nodeListTask =
            Wearable.getNodeClient(context).connectedNodes
        try {
            // Block on a task and get the result synchronously (because this is on a background thread).
            val nodes =
                Tasks.await(
                    nodeListTask
                )
            Log.e(TAG_GET_NODES, "Task fetched nodes")
            for (node in nodes) {
                Log.e(TAG_GET_NODES, "inside loop")
                nodeResults.add(node.id)
                try {
                    val nodeId = node.id
                    // Set the data of the message to be the bytes of the Uri.
                    val payload: ByteArray = wearableAppCheckPayload.toByteArray()
                    // Send the rpc
                    // Instantiates clients without member variables, as clients are inexpensive to
                    // create. (They are cached and shared between GoogleApi instances.)
                    val sendMessageTask =
                        Wearable.getMessageClient(context)
                            .sendMessage(nodeId, APP_OPEN_WEARABLE_PAYLOAD_PATH, payload)
                    try {
                        // Block on a task and get the result synchronously (because this is on a background thread).
                        val result = Tasks.await(sendMessageTask)
                        Log.d(TAG_GET_NODES, "send message result : $result")
                        resBool[0] = true
                        //Wait for 1000 ms/1 sec for the acknowledgement message
                        //Wait 1
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(100)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 1")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 2
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(150)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 2")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 3
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(200)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 3")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 4
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(250)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 4")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        //Wait 5
                        if (currentAckFromWearForAppOpenCheck != wearableAppCheckPayloadReturnACK) {
                            Thread.sleep(350)
                            Log.d(TAG_GET_NODES, "ACK thread sleep 5")
                        }
                        if (currentAckFromWearForAppOpenCheck == wearableAppCheckPayloadReturnACK) {
                            resBool[1] = true
                            return resBool
                        }
                        resBool[1] = false
                        Log.d(
                            TAG_GET_NODES,
                            "ACK thread timeout, no message received from the wearable "
                        )
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                } catch (e1: Exception) {
                    Log.d(TAG_GET_NODES, "send message exception")
                    e1.printStackTrace()
                }
            } //end of for loop
        } catch (exception: Exception) {
            Log.e(TAG_GET_NODES, "Task failed: $exception")
            exception.printStackTrace()
        }
        return resBool
    }


    override fun onDataChanged(p0: DataEventBuffer) {
    }

    @SuppressLint("SetTextI18n")
    override fun onMessageReceived(p0: MessageEvent) {
        try {
//            var binding : FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
            val s =
                String(p0.data, StandardCharsets.UTF_8)
            val messageEventPath: String = p0.path
            Log.d(
                TAG_MESSAGE_RECEIVED,
                "onMessageReceived() Received a message from watch:"
                        + p0.requestId
                        + " "
                        + messageEventPath
                        + " "
                        + s
            )
            if (messageEventPath == APP_OPEN_WEARABLE_PAYLOAD_PATH) {
                currentAckFromWearForAppOpenCheck = s
                Log.d(
                    TAG_MESSAGE_RECEIVED,
                    "Received acknowledgement message that app is open in wear"
                )

                messageEvent = p0
                wearableNodeUri = p0.sourceNodeId
            } else if (messageEventPath.isNotEmpty() && messageEventPath == MESSAGE_ITEM_RECEIVED_PATH) {
                try {
                    binding.appBarMainhome.heartRateTextView.visibility = View.VISIBLE
                    binding.appBarMainhome.calorieTextView.visibility = View.VISIBLE
                    binding.appBarMainhome.speedTextView.visibility = View.VISIBLE
                    binding.appBarMainhome.distanceTextView.visibility = View.VISIBLE
                    binding.appBarMainhome.lapsTextView.visibility = View.VISIBLE
                    Log.d("sibal",s)
                    var tempS = ""
                    var Count = 0
                    for(i in 0..s.length-1 step(1)){
                        if(s[i] == 'A'){
                            if(Count == 0){
                                binding.appBarMainhome.heartRateTextView.text = tempS
                            }
                            else if(Count == 1){
                                binding.appBarMainhome.calorieTextView.text = tempS
                            }
                            else if(Count == 2){
                                binding.appBarMainhome.distanceTextView.text = tempS
                            }
                            else if(Count == 3){
                                binding.appBarMainhome.lapsTextView.text = tempS
                            }
                            else if(Count == 4) {
                                binding.appBarMainhome.speedTextView.text = tempS
                            }
                            tempS = ""
                            Count++
                        }
                        else {
                            tempS+=s[i]
                        }
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("receive1", "Handled")
        }
    }

    override fun onCapabilityChanged(p0: CapabilityInfo) {
    }


    override fun onPause() {
        super.onPause()
        try {
            Wearable.getDataClient(activityContext!!).removeListener(this)
            Wearable.getMessageClient(activityContext!!).removeListener(this)
            Wearable.getCapabilityClient(activityContext!!).removeListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onResume() {
        super.onResume()
        try {
            Wearable.getDataClient(activityContext!!).addListener(this)
            Wearable.getMessageClient(activityContext!!).addListener(this)
            Wearable.getCapabilityClient(activityContext!!)
                .addListener(this, Uri.parse("wear://"), CapabilityClient.FILTER_REACHABLE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainhome, menu)
        return true
    }


    // ActionBar menu클릭 했을 때 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.home -> {
                homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_mainhome, homeFragment)
                    .commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


