package com.example.exercise.ui.exerciserecord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciserecordViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Exerciserecord Fragment"
    }
    val text: LiveData<String> = _text
}