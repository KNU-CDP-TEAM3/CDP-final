package com.example.exercise.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.exercise.CustomDialog
import com.example.exercise.MainActivity
import com.example.exercise.R
import com.example.exercise.databinding.FragmentMyPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment(R.layout.fragment_my_page) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileNameEdit.setOnClickListener {
            val dialog = CustomDialog(requireContext())
            dialog.showDia()
            dialog.setOnClickListener(object: CustomDialog.ButtonClickListener{
                override fun onClicked(name: String) {
                    binding.profileNameEdit.text = name
                    Log.d("MyPageFragment", name)
                    val mActivity = activity as MainActivity
                    mActivity.receiveNameData(name)
                }
            })
        }
        binding.profileAgeEdit.setOnClickListener {
            val dialog = CustomDialog(requireContext())
            dialog.showDia()
            dialog.setOnClickListener(object: CustomDialog.ButtonClickListener{
                override fun onClicked(age: String) {
                    binding.profileAgeEdit.text = age
                    Log.d("MyPageFragment", age)
                    val mActivity = activity as MainActivity
                    mActivity.receiveAgeData(age)
                }
            })
        }
        binding.profileHeightEdit.setOnClickListener {
            val dialog = CustomDialog(requireContext())
            dialog.showDia()
            dialog.setOnClickListener(object: CustomDialog.ButtonClickListener{
                override fun onClicked(height: String) {
                    binding.profileHeightEdit.text = height
                    Log.d("MyPageFragment", height)
                    val mActivity = activity as MainActivity
                    mActivity.receiveHeightData(height)
                }
            })
        }
        binding.profileWeightEdit.setOnClickListener {
            val dialog = CustomDialog(requireContext())
            dialog.showDia()
            dialog.setOnClickListener(object: CustomDialog.ButtonClickListener{
                override fun onClicked(weight: String) {
                    binding.profileWeightEdit.text = weight
                    Log.d("MyPageFragment", weight)
                    val mActivity = activity as MainActivity
                    mActivity.receiveWeightData(weight)
                }
            })
        }
    }

    fun changeName(name:String) {
        if (name != null) {
            Log.d("huhuhuh", name)
            //binding.profileNameEdit.text = name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}