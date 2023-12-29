package com.mealotopia.client.view.activity.HomeActivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mealotopia.client.R
import com.mealotopia.client.databinding.FragmentListMealBinding

class ListMealFragment : Fragment() {

    private val TAG = ListMealFragment::class.java.simpleName
    private var _binding: FragmentListMealBinding?= null
    private val binding get() = _binding!!
    private var input: String ?= null

    companion object {
        const val INPUT_SENT = "INPUT_SENT"
        fun newInstance(input: String): ListMealFragment{
            val fragment = ListMealFragment()
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
        _binding = FragmentListMealBinding.inflate(layoutInflater, container, false)
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