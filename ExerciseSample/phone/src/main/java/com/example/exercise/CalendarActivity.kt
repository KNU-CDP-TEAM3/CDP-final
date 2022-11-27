package com.example.exercise

import android.annotation.SuppressLint
import java.io.FileInputStream
import java.io.FileOutputStream

import android.view.View
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exercise.R

class CalendarActivity : AppCompatActivity() {
    var userID: String = "userID"
    lateinit var fname: String
    lateinit var fnameC: String
    lateinit var fname2: String
    lateinit var fnameC2: String
    lateinit var fname3: String
    lateinit var fnameC3: String
    lateinit var str: String
    lateinit var strC: String
    lateinit var str2: String
    lateinit var strC2: String
    lateinit var str3: String
    lateinit var strC3: String
    lateinit var calendarView: CalendarView
    lateinit var updateBtn: Button
    lateinit var updateBtn2: Button
    lateinit var updateBtn3: Button
    lateinit var deleteBtn:Button
    lateinit var deleteBtn2:Button
    lateinit var deleteBtn3:Button
    lateinit var saveBtn:Button
    lateinit var saveBtn2:Button
    lateinit var saveBtn3:Button
    lateinit var diaryContent:TextView
    lateinit var diaryContentCalories:TextView
    lateinit var diaryContentCalories2:TextView
    lateinit var diaryContentCalories3:TextView
    lateinit var diaryContent2:TextView
    lateinit var diaryContent3:TextView
    lateinit var title:TextView
    lateinit var diaryTextView:TextView
    lateinit var contextEditTextBreakfast: EditText
    lateinit var contextEditTextBreakfastCalories: EditText
    lateinit var contextEditTextLunch: EditText
    lateinit var contextEditTextLunchCalories: EditText
    lateinit var contextEditTextDinner: EditText
    lateinit var contextEditTextDinnerCalories: EditText
    lateinit var breakfastBtn: Button
    lateinit var lunchBtn: Button
    lateinit var dinnerBtn: Button
    lateinit var calories: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        // UI값 생성
        calendarView=findViewById(R.id.calendarView)
        diaryTextView=findViewById(R.id.diaryTextView)
        saveBtn=findViewById(R.id.saveBtn)
        saveBtn2=findViewById(R.id.saveBtn2)
        saveBtn3=findViewById(R.id.saveBtn3)
        deleteBtn=findViewById(R.id.deleteBtn)
        deleteBtn2=findViewById(R.id.deleteBtn2)
        deleteBtn3=findViewById(R.id.deleteBtn3)
        updateBtn=findViewById(R.id.updateBtn)
        updateBtn2=findViewById(R.id.updateBtn2)
        updateBtn3=findViewById(R.id.updateBtn3)
        diaryContent=findViewById(R.id.diaryContent)
        diaryContent2=findViewById(R.id.diaryContent2)
        diaryContent3=findViewById(R.id.diaryContent3)
        diaryContentCalories=findViewById(R.id.diaryContentCalories)
        diaryContentCalories2=findViewById(R.id.diaryContentCalories2)
        diaryContentCalories3=findViewById(R.id.diaryContentCalories3)

        breakfastBtn=findViewById(R.id.breakfastBtn)
        lunchBtn=findViewById(R.id.lunchBtn)
        dinnerBtn=findViewById(R.id.dinnerBtn)

        title=findViewById(R.id.title)
        contextEditTextBreakfast=findViewById(R.id.contextEditTextBreakfast)
        contextEditTextBreakfastCalories=findViewById(R.id.contextEditTextBreakfastCalories)
        contextEditTextLunch=findViewById(R.id.contextEditTextLunch)
        contextEditTextLunchCalories=findViewById(R.id.contextEditTextLunchCalories)
        contextEditTextDinner=findViewById(R.id.contextEditTextDinner)
        contextEditTextDinnerCalories=findViewById(R.id.contextEditTextDinnerCalories)
        calories=findViewById(R.id.calories)
        title.text = "식단 관리"

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calories.visibility=View.VISIBLE
            diaryTextView.visibility = View.VISIBLE
            saveBtn.visibility = View.VISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            contextEditTextBreakfast.visibility = View.VISIBLE
            contextEditTextBreakfastCalories.visibility = View.VISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            diaryContent.visibility = View.INVISIBLE
            diaryContentCalories.visibility = View.INVISIBLE
            diaryContentCalories2.visibility = View.INVISIBLE
            diaryContentCalories3.visibility = View.INVISIBLE
            diaryContent2.visibility = View.INVISIBLE
            diaryContent3.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            updateBtn2.visibility = View.INVISIBLE
            updateBtn3.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            deleteBtn2.visibility = View.INVISIBLE
            deleteBtn3.visibility = View.INVISIBLE
            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
            diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            contextEditTextBreakfast.setText("")
            contextEditTextBreakfastCalories.setText("")
            checkDay(year, month, dayOfMonth, userID)
            breakfastBtn.setOnClickListener {
                diaryTextView.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                saveBtn2.visibility = View.INVISIBLE
                saveBtn3.visibility = View.INVISIBLE
                contextEditTextBreakfast.visibility = View.VISIBLE
                contextEditTextBreakfastCalories.visibility = View.VISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
                diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
                contextEditTextBreakfast.setText("")
                contextEditTextBreakfastCalories.setText("")
                checkDay(year, month, dayOfMonth, userID)
            }
            lunchBtn.setOnClickListener {
                diaryTextView.visibility = View.VISIBLE
                saveBtn2.visibility = View.VISIBLE
                saveBtn3.visibility = View.INVISIBLE
                saveBtn.visibility = View.INVISIBLE
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.VISIBLE
                contextEditTextLunchCalories.visibility = View.VISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
                diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
                contextEditTextLunch.setText("")
                contextEditTextLunchCalories.setText("")
                checkDay2(year, month, dayOfMonth, userID)
            }
            dinnerBtn.setOnClickListener {
                diaryTextView.visibility = View.VISIBLE
                saveBtn2.visibility = View.INVISIBLE
                saveBtn3.visibility = View.VISIBLE
                saveBtn.visibility = View.INVISIBLE
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.VISIBLE
                contextEditTextDinnerCalories.visibility = View.VISIBLE
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
                diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
                contextEditTextDinner.setText("")
                contextEditTextDinnerCalories.setText("")
                checkDay3(year, month, dayOfMonth, userID)
            }
        }
        saveBtn.setOnClickListener {
            saveDiary(fname,fnameC)
            contextEditTextBreakfast.visibility = View.INVISIBLE
            contextEditTextBreakfastCalories.visibility = View.INVISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            updateBtn.visibility = View.VISIBLE
            updateBtn2.visibility = View.INVISIBLE
            updateBtn3.visibility = View.INVISIBLE
            deleteBtn.visibility = View.VISIBLE
            deleteBtn2.visibility = View.INVISIBLE
            deleteBtn3.visibility = View.INVISIBLE
            str = contextEditTextBreakfast.text.toString()
            strC = contextEditTextBreakfastCalories.text.toString()
            diaryContent.text = str
            diaryContentCalories.text = strC
            diaryContent.visibility = View.VISIBLE
            diaryContentCalories.visibility = View.VISIBLE
            diaryContentCalories2.visibility = View.INVISIBLE
            diaryContentCalories3.visibility = View.INVISIBLE
            diaryContent2.visibility = View.INVISIBLE
            diaryContent3.visibility = View.INVISIBLE

            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
        }
        saveBtn2.setOnClickListener {
            saveDiary2(fname2,fnameC2)
            contextEditTextBreakfast.visibility = View.INVISIBLE
            contextEditTextBreakfastCalories.visibility = View.INVISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            updateBtn2.visibility = View.VISIBLE
            updateBtn3.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            deleteBtn2.visibility = View.VISIBLE
            deleteBtn3.visibility = View.INVISIBLE
            str2 = contextEditTextLunch.text.toString()
            strC2 = contextEditTextLunchCalories.text.toString()
            diaryContent2.text = str2
            diaryContentCalories2.text = strC2
            diaryContent2.visibility = View.VISIBLE
            diaryContentCalories2.visibility = View.VISIBLE
            diaryContent.visibility = View.INVISIBLE
            diaryContentCalories.visibility = View.INVISIBLE
            diaryContent3.visibility = View.INVISIBLE
            diaryContentCalories3.visibility = View.INVISIBLE

            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
        }
        saveBtn3.setOnClickListener {
            saveDiary3(fname3,fnameC3)
            contextEditTextBreakfast.visibility = View.INVISIBLE
            contextEditTextBreakfastCalories.visibility = View.INVISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            updateBtn2.visibility = View.INVISIBLE
            updateBtn3.visibility = View.VISIBLE
            deleteBtn.visibility = View.INVISIBLE
            deleteBtn2.visibility = View.INVISIBLE
            deleteBtn3.visibility = View.VISIBLE
            str3 = contextEditTextDinner.text.toString()
            strC3 = contextEditTextDinnerCalories.text.toString()
            diaryContent3.text = str3
            diaryContentCalories3.text = strC3
            diaryContent3.visibility = View.VISIBLE
            diaryContentCalories3.visibility = View.VISIBLE
            diaryContent.visibility = View.INVISIBLE
            diaryContentCalories.visibility = View.INVISIBLE
            diaryContent2.visibility = View.INVISIBLE
            diaryContentCalories2.visibility = View.INVISIBLE

            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
        }
    }

    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        //저장할 파일 이름설정
        fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
        fnameC = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay +"C"+ ".txt"

        var fileInputStream: FileInputStream
        var fileInputStreamC: FileInputStream
        try {
            fileInputStream = openFileInput(fname)
            fileInputStreamC = openFileInput(fnameC)
            val fileData = ByteArray(fileInputStream.available())
            val fileDataC = ByteArray(fileInputStreamC.available())
            fileInputStream.read(fileData)
            fileInputStreamC.read(fileDataC)
            fileInputStream.close()
            fileInputStreamC.close()
            str = String(fileData)
            strC = String(fileDataC)
            contextEditTextBreakfast.visibility = View.INVISIBLE
            contextEditTextBreakfastCalories.visibility = View.INVISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
            diaryContent.text = str
            diaryContentCalories.text = strC
            diaryContent.visibility = View.VISIBLE
            diaryContentCalories.visibility = View.VISIBLE
            diaryContent2.visibility = View.INVISIBLE
            diaryContent3.visibility = View.INVISIBLE
            diaryContentCalories2.visibility = View.INVISIBLE
            diaryContentCalories3.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            updateBtn.visibility = View.VISIBLE
            updateBtn2.visibility = View.INVISIBLE
            updateBtn3.visibility = View.INVISIBLE
            deleteBtn.visibility = View.VISIBLE
            deleteBtn2.visibility = View.INVISIBLE
            deleteBtn3.visibility = View.INVISIBLE
            updateBtn.setOnClickListener {
                contextEditTextBreakfast.visibility = View.VISIBLE
                contextEditTextBreakfastCalories.visibility = View.VISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.INVISIBLE
                lunchBtn.visibility = View.INVISIBLE
                dinnerBtn.visibility = View.INVISIBLE
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE

                contextEditTextBreakfast.setText(str)
                contextEditTextBreakfastCalories.setText(strC)
                saveBtn.visibility = View.VISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                diaryContent.text = contextEditTextBreakfast.text
                diaryContentCalories.text = contextEditTextBreakfastCalories.text
            }
            deleteBtn.setOnClickListener {
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                contextEditTextBreakfast.setText("")
                contextEditTextBreakfastCalories.setText("")
                contextEditTextBreakfast.visibility = View.VISIBLE
                contextEditTextBreakfastCalories.visibility = View.VISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                saveBtn2.visibility = View.INVISIBLE
                saveBtn3.visibility = View.INVISIBLE
                removeDiary(fname,fnameC)
            }
            if (diaryContent.text == null) {
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                saveBtn2.visibility = View.INVISIBLE
                saveBtn3.visibility = View.INVISIBLE
                contextEditTextBreakfast.visibility = View.VISIBLE
                contextEditTextBreakfastCalories.visibility = View.VISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkDay2(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        //저장할 파일 이름설정
        fname2 = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay +2+ ".txt"
        fnameC2 = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay +"C"+ 2+".txt"

        var fileInputStream: FileInputStream
        var fileInputStreamC: FileInputStream
        try {
            fileInputStream = openFileInput(fname2)
            fileInputStreamC = openFileInput(fnameC2)
            val fileData = ByteArray(fileInputStream.available())
            val fileDataC = ByteArray(fileInputStreamC.available())
            fileInputStream.read(fileData)
            fileInputStreamC.read(fileDataC)
            fileInputStream.close()
            fileInputStreamC.close()
            str2 = String(fileData)
            strC2 = String(fileDataC)
            contextEditTextBreakfast.visibility = View.INVISIBLE
            contextEditTextBreakfastCalories.visibility = View.INVISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
            diaryContent2.text = str2
            diaryContentCalories2.text = strC2
            diaryContent.visibility = View.INVISIBLE
            diaryContentCalories.visibility = View.INVISIBLE
            diaryContent2.visibility = View.VISIBLE
            diaryContent3.visibility = View.INVISIBLE
            diaryContentCalories2.visibility = View.VISIBLE
            diaryContentCalories3.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            updateBtn2.visibility = View.VISIBLE
            updateBtn3.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            deleteBtn2.visibility = View.VISIBLE
            deleteBtn3.visibility = View.INVISIBLE
            updateBtn2.setOnClickListener {
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.VISIBLE
                contextEditTextLunchCalories.visibility = View.VISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.INVISIBLE
                lunchBtn.visibility = View.INVISIBLE
                dinnerBtn.visibility = View.INVISIBLE
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE

                contextEditTextLunch.setText(str2)
                contextEditTextLunchCalories.setText(strC2)
                saveBtn2.visibility = View.VISIBLE
                updateBtn2.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                diaryContent2.text = contextEditTextLunch.text
                diaryContentCalories2.text = contextEditTextLunchCalories.text
            }
            deleteBtn2.setOnClickListener {
                diaryContent2.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                contextEditTextLunch.setText("")
                contextEditTextLunchCalories.setText("")
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.VISIBLE
                contextEditTextLunchCalories.visibility = View.VISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
                saveBtn.visibility = View.INVISIBLE
                saveBtn2.visibility = View.VISIBLE
                saveBtn3.visibility = View.INVISIBLE
                removeDiary2(fname2,fnameC2)
            }
            if (diaryContent2.text == null) {
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                saveBtn.visibility = View.INVISIBLE
                saveBtn2.visibility = View.VISIBLE
                saveBtn3.visibility = View.INVISIBLE
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.VISIBLE
                contextEditTextLunchCalories.visibility = View.VISIBLE
                contextEditTextDinner.visibility = View.INVISIBLE
                contextEditTextDinnerCalories.visibility = View.INVISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkDay3(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        //저장할 파일 이름설정
        fname3 = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay +3+ ".txt"
        fnameC3 = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay +"C"+3+ ".txt"

        var fileInputStream: FileInputStream
        var fileInputStreamC: FileInputStream
        try {
            fileInputStream = openFileInput(fname3)
            fileInputStreamC = openFileInput(fnameC3)
            val fileData = ByteArray(fileInputStream.available())
            val fileDataC = ByteArray(fileInputStreamC.available())
            fileInputStream.read(fileData)
            fileInputStreamC.read(fileDataC)
            fileInputStream.close()
            fileInputStreamC.close()
            str3 = String(fileData)
            strC3 = String(fileDataC)
            contextEditTextBreakfast.visibility = View.INVISIBLE
            contextEditTextBreakfastCalories.visibility = View.INVISIBLE
            contextEditTextLunch.visibility = View.INVISIBLE
            contextEditTextLunchCalories.visibility = View.INVISIBLE
            contextEditTextDinner.visibility = View.INVISIBLE
            contextEditTextDinnerCalories.visibility = View.INVISIBLE
            breakfastBtn.visibility = View.VISIBLE
            lunchBtn.visibility = View.VISIBLE
            dinnerBtn.visibility = View.VISIBLE
            diaryContent.text = str3
            diaryContentCalories.text = strC3
            diaryContent.visibility = View.INVISIBLE
            diaryContentCalories.visibility = View.INVISIBLE
            diaryContent2.visibility = View.INVISIBLE
            diaryContent3.visibility = View.VISIBLE
            diaryContentCalories2.visibility = View.INVISIBLE
            diaryContentCalories3.visibility = View.VISIBLE
            saveBtn.visibility = View.INVISIBLE
            saveBtn2.visibility = View.INVISIBLE
            saveBtn3.visibility = View.INVISIBLE
            updateBtn3.visibility = View.VISIBLE
            updateBtn2.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            deleteBtn3.visibility = View.VISIBLE
            deleteBtn2.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            updateBtn3.setOnClickListener {
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.VISIBLE
                contextEditTextDinnerCalories.visibility = View.VISIBLE
                breakfastBtn.visibility = View.INVISIBLE
                lunchBtn.visibility = View.INVISIBLE
                dinnerBtn.visibility = View.INVISIBLE
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE

                contextEditTextDinner.setText(str3)
                contextEditTextDinnerCalories.setText(strC3)
                saveBtn3.visibility = View.VISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                diaryContent3.text = contextEditTextDinner.text
                diaryContentCalories3.text = contextEditTextDinnerCalories.text
            }
            deleteBtn3.setOnClickListener {
                diaryContent3.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                contextEditTextDinner.setText("")
                contextEditTextDinnerCalories.setText("")
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.VISIBLE
                contextEditTextDinnerCalories.visibility = View.VISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
                saveBtn.visibility = View.INVISIBLE
                saveBtn2.visibility = View.INVISIBLE
                saveBtn3.visibility = View.VISIBLE
                removeDiary3(fname3,fnameC3)
            }
            if (diaryContent3.text == null) {
                diaryContent.visibility = View.INVISIBLE
                diaryContentCalories.visibility = View.INVISIBLE
                diaryContentCalories2.visibility = View.INVISIBLE
                diaryContentCalories3.visibility = View.INVISIBLE
                diaryContent2.visibility = View.INVISIBLE
                diaryContent3.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                updateBtn2.visibility = View.INVISIBLE
                updateBtn3.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                deleteBtn2.visibility = View.INVISIBLE
                deleteBtn3.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                saveBtn3.visibility = View.VISIBLE
                saveBtn2.visibility = View.INVISIBLE
                saveBtn.visibility = View.INVISIBLE
                contextEditTextBreakfast.visibility = View.INVISIBLE
                contextEditTextBreakfastCalories.visibility = View.INVISIBLE
                contextEditTextLunch.visibility = View.INVISIBLE
                contextEditTextLunchCalories.visibility = View.INVISIBLE
                contextEditTextDinner.visibility = View.VISIBLE
                contextEditTextDinnerCalories.visibility = View.VISIBLE
                breakfastBtn.visibility = View.VISIBLE
                lunchBtn.visibility = View.VISIBLE
                dinnerBtn.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // 달력 내용 제거
    @SuppressLint("WrongConstant")
    fun removeDiary(readDay: String?,readDay2:String?) {
        var fileOutputStream: FileOutputStream
        var fileOutputStreamC: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            fileOutputStreamC = openFileOutput(readDay2, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            val contentC = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStreamC.write(contentC.toByteArray())
            fileOutputStream.close()
            fileOutputStreamC.close()
            diaryContent.text=content
            diaryContentCalories.text=contentC
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    @SuppressLint("WrongConstant")
    fun removeDiary2(readDay: String?,readDay2:String?) {
        var fileOutputStream: FileOutputStream
        var fileOutputStreamC: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            fileOutputStreamC = openFileOutput(readDay2, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            val contentC = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStreamC.write(contentC.toByteArray())
            fileOutputStream.close()
            fileOutputStreamC.close()
            diaryContent2.text=content
            diaryContentCalories2.text=contentC
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    @SuppressLint("WrongConstant")
    fun removeDiary3(readDay: String?,readDay2:String?) {
        var fileOutputStream: FileOutputStream
        var fileOutputStreamC: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            fileOutputStreamC = openFileOutput(readDay2, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            val contentC = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStreamC.write(contentC.toByteArray())
            fileOutputStream.close()
            fileOutputStreamC.close()
            diaryContent3.text=content
            diaryContentCalories3.text=contentC
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    // 달력 내용 추가
    @SuppressLint("WrongConstant")
    fun saveDiary(readDay: String?,readDay2: String?) {
        var fileOutputStream: FileOutputStream
        var fileOutputStreamC: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            fileOutputStreamC = openFileOutput(readDay2, MODE_NO_LOCALIZED_COLLATORS)
            val content = contextEditTextBreakfast.text.toString()
            val contentC = contextEditTextBreakfastCalories.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStreamC.write(contentC.toByteArray())
            fileOutputStream.close()
            fileOutputStreamC.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongConstant")
    fun saveDiary2(readDay: String?,readDay2: String?) {
        var fileOutputStream: FileOutputStream
        var fileOutputStreamC: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            fileOutputStreamC = openFileOutput(readDay2, MODE_NO_LOCALIZED_COLLATORS)
            val content = contextEditTextLunch.text.toString()
            val contentC = contextEditTextLunchCalories.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStreamC.write(contentC.toByteArray())
            fileOutputStream.close()
            fileOutputStreamC.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongConstant")
    fun saveDiary3(readDay: String?,readDay2: String?) {
        var fileOutputStream: FileOutputStream
        var fileOutputStreamC: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            fileOutputStreamC = openFileOutput(readDay2, MODE_NO_LOCALIZED_COLLATORS)
            val content = contextEditTextDinner.text.toString()
            val contentC = contextEditTextDinnerCalories.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStreamC.write(contentC.toByteArray())
            fileOutputStream.close()
            fileOutputStreamC.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}