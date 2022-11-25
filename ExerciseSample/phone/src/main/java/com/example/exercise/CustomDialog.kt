package com.example.exercise

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class CustomDialog(context: Context) {
    private val dialog = Dialog(context)

    fun showDia() {
        dialog.setContentView(R.layout.dialog_layout)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val editText = dialog.findViewById<EditText>(R.id.profile_edit)
        val okBtn = dialog.findViewById<Button>(R.id.finish_button)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancel_button)

        okBtn.setOnClickListener {
            onClickListener.onClicked(editText.text.toString())
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(text: String)
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }
}