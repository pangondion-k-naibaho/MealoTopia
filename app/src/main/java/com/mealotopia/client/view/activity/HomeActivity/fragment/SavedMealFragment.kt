package com.mealotopia.client.view.activity.HomeActivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mealotopia.client.R
import com.mealotopia.client.databinding.FragmentSavedMealBinding

class SavedMealFragment : Fragment() {

    private val TAG = SavedMealFragment::class.java.simpleName
    private var _binding: FragmentSavedMealBinding?= null
    private val binding get() = _binding!!

    private var input: String?= null

    companion object {
        private const val INPUT_SENT = "INPUT_SENT"

        fun newInstance(input: String): SavedMealFragment{
            val fragment = SavedMealFragment()
            fragment.input = input

            val bundle = Bundle()
            bundle.putString(INPUT_SENT, input)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSavedMealBinding.inflate(layoutInflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}