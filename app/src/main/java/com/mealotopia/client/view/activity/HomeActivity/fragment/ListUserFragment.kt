package com.mealotopia.client.view.activity.HomeActivity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mealotopia.client.R
import com.mealotopia.client.databinding.FragmentListUserBinding
import com.mealotopia.client.model.data_class.user.ListUserResponse
import com.mealotopia.client.view.activity.HomeActivity.HomeCommunicator
import com.mealotopia.client.view.adapter.ListUserAdapter
import com.mealotopia.client.viewmodel.HomeViewModel

class ListUserFragment : Fragment() {
    private val TAG = ListUserFragment::class.java.simpleName
    private var _binding: FragmentListUserBinding?=null

    private val binding get() = _binding!!

    private var input: String?= null
    private var page: Int?= null
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var homeCommunicator: HomeCommunicator

    companion object {
        private const val INPUT_SENT = "INPUT_SENT"

        fun newInstance(input: String): ListUserFragment{
            val fragment = ListUserFragment()
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
        _binding = FragmentListUserBinding.inflate(layoutInflater, container, false)
        homeCommunicator = activity as HomeCommunicator
        setUpView()
        return binding.root
    }

    private fun setUpView(){
        page = 1
        homeViewModel.getListUser(page!!)

        homeViewModel.listUserResponse.observe(this@ListUserFragment.requireActivity(), {listUser ->
            setUpListUser(listUser)
        })

        homeViewModel.isGettingListUserLoading.observe(this@ListUserFragment.requireActivity(), {
            if(it) homeCommunicator.onListDisplayingLoadingStarted() else homeCommunicator.onListDisplayingLoadingFinished()
        })
    }

    private fun setUpListUser(listUser: ListUserResponse){
        val myLayoutManager = LinearLayoutManager(this@ListUserFragment.requireActivity())

        binding.rvListUser.apply {
            val userAdapter = ListUserAdapter(listUser.data!!.toMutableList())
            layoutManager = myLayoutManager
            adapter = userAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = myLayoutManager.childCount
                    val totalItemCount = myLayoutManager.itemCount
                    val firstVisibleItemPosition = myLayoutManager.findFirstVisibleItemPosition()

                    if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= listUser.data!!.size
                        ){
                        page = page?.plus(1)
                        homeViewModel.getListUserMore(page!!)
                        homeViewModel.listUserResponse2.observe(this@ListUserFragment.requireActivity(), {listUser2->
                            if(listUser2.data != null){
                                userAdapter.addItem(listUser2.data!!)
                            }
                        })
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}