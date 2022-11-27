package com.example.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.exercise.databinding.ActivityInfoExerciseBinding

class InfoExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}