package com.mealotopia.client.view.activity.HomeActivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mealotopia.client.R
import com.mealotopia.client.databinding.FragmentListMealBinding
import com.mealotopia.client.model.data_class.meal.DetailMealResponse
import com.mealotopia.client.model.data_class.meal.ListMealResponse
import com.mealotopia.client.view.activity.HomeActivity.HomeCommunicator
import com.mealotopia.client.view.adapter.ListMealAdapter
import com.mealotopia.client.view.advanced_ui.InputSearchView
import com.mealotopia.client.viewmodel.HomeViewModel

class ListMealFragment : Fragment() {

    private val TAG = ListMealFragment::class.java.simpleName
    private var _binding: FragmentListMealBinding?= null
    private val binding get() = _binding!!
    private var input: String ?= null
    private var inputString: String?= null
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var homeCommunicator: HomeCommunicator

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
        homeCommunicator = activity as HomeCommunicator
        setUpView()
        return binding.root
    }

    private fun setUpView(){
        inputString = "a"
        homeViewModel.getListMeal(inputString!!)

        homeViewModel.listMealResponse.observe(this@ListMealFragment.requireActivity(), {listMeal->
            setUpListMeal(listMeal)
        })

        homeViewModel.isLoading.observe(this@ListMealFragment.requireActivity(),{
            if(it) homeCommunicator.onListDisplayingLoadingStarted() else homeCommunicator.onListDisplayingLoadingFinished()
        })

    }

    private fun setUpListMeal(listMeal: ListMealResponse){
        val myLayoutManager = LinearLayoutManager(this@ListMealFragment.requireActivity())

        val mealAdapter = ListMealAdapter(
            listMeal.meals!!.toMutableList(),
            object: ListMealAdapter.ItemListener{
                override fun onItemClicked(item: DetailMealResponse) {
                    homeCommunicator.onDeliverMealToDetail(item)
                }
            })

        binding.rvMeal.apply {
            layoutManager = myLayoutManager
            adapter = mealAdapter
        }

        binding.isvMeal.apply {
            setTextHelper(getString(R.string.tvIsv_Hint))
            setListener(object: InputSearchView.InputSearchListener{
                override fun onClickSearch() {
                    inputString = getText()

                    homeViewModel.getListMealMore(inputString!!)

                    homeViewModel.listMealResponse2.observe(this@ListMealFragment.requireActivity(), {listMeal2->
                        if(listMeal2.meals != null){
                            mealAdapter.updateItem(listMeal2.meals!!)
                        }else{
                            Toast.makeText(this@ListMealFragment.requireActivity(), "$inputString shows no result", Toast.LENGTH_SHORT).show()
                        }
                    })
                }

                override fun onClearSearch() {
                    clearText()

                    homeViewModel.getListMealMore("a")

                    homeViewModel.listMealResponse2.observe(this@ListMealFragment.requireActivity(), {listMeal2->
                        if(listMeal2.meals != null){
                            mealAdapter.updateItem(listMeal2.meals!!)
                        }
                    })

                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}