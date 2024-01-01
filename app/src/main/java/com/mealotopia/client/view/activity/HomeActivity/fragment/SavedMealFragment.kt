package com.mealotopia.client.view.activity.HomeActivity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mealotopia.client.R
import com.mealotopia.client.databinding.FragmentSavedMealBinding
import com.mealotopia.client.model.data_class.meal.DetailMealResponse
import com.mealotopia.client.view.activity.DetailMealActivity.DetailMealActivity
import com.mealotopia.client.view.adapter.ListMealAdapter
import com.mealotopia.client.viewmodel.DetailnBookmarkViewModel

class SavedMealFragment : Fragment() {

    private val TAG = SavedMealFragment::class.java.simpleName
    private var _binding: FragmentSavedMealBinding?= null
    private val binding get() = _binding!!

    private var input: String?= null
    private var arrListBookmarkedMeal = ArrayList<DetailMealResponse>()
    private lateinit var detailnBookmarkViewModel: DetailnBookmarkViewModel

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
        detailnBookmarkViewModel = ViewModelProvider(this)[DetailnBookmarkViewModel::class.java]

        detailnBookmarkViewModel.getBookmarkedMeal().observe(this@SavedMealFragment.requireActivity(), {bookmarkedMeals->
            bookmarkedMeals.forEach{
                arrListBookmarkedMeal.addAll(listOf(it))
            }

            if(bookmarkedMeals.count() != 0){
                setUpEmptyContent(false)
                val listMealAdapter = ListMealAdapter(arrListBookmarkedMeal, object:ListMealAdapter.ItemListener{
                    override fun onItemClicked(item: DetailMealResponse) {
                        startActivity(
                            DetailMealActivity.newIntent(this@SavedMealFragment.requireActivity(), item)
                        )
                    }
                })

                binding.rvMeal.apply {
                    adapter = listMealAdapter
                    layoutManager = LinearLayoutManager(this@SavedMealFragment.requireActivity())
                }

            }else{
                setUpEmptyContent(true)
            }
        })


    }

    private fun setUpEmptyContent(state: Boolean){
        if(state){
            binding.rvMeal.visibility = View.GONE
            binding.tvAnnouncement.visibility = View.VISIBLE
        }else{
            binding.rvMeal.visibility = View.VISIBLE
            binding.tvAnnouncement.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        arrListBookmarkedMeal.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}