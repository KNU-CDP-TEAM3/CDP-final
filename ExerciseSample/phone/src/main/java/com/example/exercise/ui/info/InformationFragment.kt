package com.example.exercise.ui.info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise.InfoDietActivity
import com.example.exercise.InfoExerciseActivity
import com.example.exercise.R
import com.example.exercise.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonExercise.setOnClickListener {
            activity?.let {
                val intent = Intent(context, InfoExerciseActivity::class.java)
                startActivity(intent)
            }
        }
        binding.buttonDiet.setOnClickListener {
            activity?.let {
                val intent = Intent(context, InfoDietActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}