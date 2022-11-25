package com.example.exercise

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class ExerciseActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper()) // 메인 루퍼를 넣어주면 메인 스레드와 연결

    private val _exeEditText: EditText by lazy {
        findViewById<EditText>(R.id.exe1_memo)
        findViewById<EditText>(R.id.exe2_memo)
        findViewById<EditText>(R.id.exe3_memo)
        findViewById<EditText>(R.id.exe4_memo)
        findViewById<EditText>(R.id.exe5_memo)
        findViewById<EditText>(R.id.exe6_memo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val exe1memo = findViewById<EditText>(R.id.exe1_memo)
        val exe2memo = findViewById<EditText>(R.id.exe2_memo)
        val exe3memo = findViewById<EditText>(R.id.exe3_memo)
        val exe4memo = findViewById<EditText>(R.id.exe4_memo)
        val exe5memo = findViewById<EditText>(R.id.exe5_memo)
        val exe6memo = findViewById<EditText>(R.id.exe6_memo)

        val detailPreferences1 = getSharedPreferences("exercise1", Context.MODE_PRIVATE)
        val detailPreferences2 = getSharedPreferences("exercise2", Context.MODE_PRIVATE)
        val detailPreferences3 = getSharedPreferences("exercise3", Context.MODE_PRIVATE)
        val detailPreferences4 = getSharedPreferences("exercise4", Context.MODE_PRIVATE)
        val detailPreferences5 = getSharedPreferences("exercise5", Context.MODE_PRIVATE)
        val detailPreferences6 = getSharedPreferences("exercise6", Context.MODE_PRIVATE)

        exe1memo.setText(detailPreferences1.getString("detail", ""))
        exe2memo.setText(detailPreferences2.getString("detail", ""))
        exe3memo.setText(detailPreferences3.getString("detail", ""))
        exe4memo.setText(detailPreferences4.getString("detail", ""))
        exe5memo.setText(detailPreferences5.getString("detail", ""))
        exe6memo.setText(detailPreferences6.getString("detail", ""))

        val runnable = Runnable {
            getSharedPreferences("exercise1", Context.MODE_PRIVATE).edit {
                putString("detail", exe1memo.text.toString())
                apply()
            }
            getSharedPreferences("exercise2", Context.MODE_PRIVATE).edit {
                putString("detail", exe2memo.text.toString())
                apply()
            }
            getSharedPreferences("exercise3", Context.MODE_PRIVATE).edit {
                putString("detail", exe3memo.text.toString())
                apply()
            }
            getSharedPreferences("exercise4", Context.MODE_PRIVATE).edit {
                putString("detail", exe4memo.text.toString())
                apply()
            }
            getSharedPreferences("exercise5", Context.MODE_PRIVATE).edit {
                putString("detail", exe5memo.text.toString())
                apply()
            }
            getSharedPreferences("exercise6", Context.MODE_PRIVATE).edit {
                putString("detail", exe6memo.text.toString())
                apply()
            }

            Log.d("ExerciseActivity", "1 SAVE!!! ${exe1memo.text.toString()}")
            Log.d("ExerciseActivity", "2 SAVE!!! ${exe2memo.text.toString()}")
            Log.d("ExerciseActivity", "3 SAVE!!! ${exe3memo.text.toString()}")
            Log.d("ExerciseActivity", "4 SAVE!!! ${exe4memo.text.toString()}")
            Log.d("ExerciseActivity", "5 SAVE!!! ${exe5memo.text.toString()}")
            Log.d("ExerciseActivity", "6 SAVE!!! ${exe6memo.text.toString()}")
        }

        // 수정 할때 마다 저장
        exe1memo.addTextChangedListener {
            Log.d("ExerciseActivity", "1 TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
        exe2memo.addTextChangedListener {
            Log.d("ExerciseActivity", "2 TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
        exe3memo.addTextChangedListener {
            Log.d("ExerciseActivity", "3 TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
        exe4memo.addTextChangedListener {
            Log.d("ExerciseActivity", "4 TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
        exe5memo.addTextChangedListener {
            Log.d("ExerciseActivity", "5 TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
        exe6memo.addTextChangedListener {
            Log.d("ExerciseActivity", "6 TextChanged :: $it")

            handler.removeCallbacks(runnable) // 이전에 팬딩되어있는 runnable이 있다면 제거
            //몇 초 이후에 runnable을 실행
            handler.postDelayed(runnable, 500) // 0.5 초 이후에 실행
        }
    }
}