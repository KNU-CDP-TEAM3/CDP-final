package com.example.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.exercise.databinding.ActivityInfoDietBinding

class InfoDietActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoDietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoDietBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}