package com.mealotopia.client.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mealotopia.client.model.data_class.meal.ListMealResponse
import com.mealotopia.client.model.data_class.user.ListUserResponse
import com.mealotopia.client.model.networking.ApiConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class HomeViewModel: ViewModel() {
    private val TAG = LoginViewModel::class.java.simpleName

    private var _logoutResponse = MutableLiveData<Void>()
    val logoutResponse : LiveData<Void> = _logoutResponse

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail : LiveData<Boolean> = _isFail

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean> = _isSuccess

    private var _listUserResponse = MutableLiveData<ListUserResponse>()
    val listUserResponse : LiveData<ListUserResponse> = _listUserResponse

    private var _listUserResponse2 = MutableLiveData<ListUserResponse>()
    val listUserResponse2 : LiveData<ListUserResponse> = _listUserResponse2

    private var _isGettingListUserLoading = MutableLiveData<Boolean>()
    val isGettingListUserLoading: LiveData<Boolean> = _isGettingListUserLoading

    private var _isGettingListUserFail = MutableLiveData<Boolean>()
    val isGettingListUserFail : LiveData<Boolean> = _isGettingListUserFail

    private var _listMealResponse = MutableLiveData<ListMealResponse>()
    val listMealResponse : LiveData<ListMealResponse> = _listMealResponse

    private var _listMealResponse2 = MutableLiveData<ListMealResponse>()
    val listMealResponse2 : LiveData<ListMealResponse> = _listMealResponse2

    fun logOutUser(){
        _isLoading.value = true
        val client = ApiConfig.getAuthenticationApiService().logoutUser()
        client.enqueue(object: retrofit2.Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _isSuccess.value = true
                    _logoutResponse.value = response.body()
                    Log.d(TAG, "Success")
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getListUser(page: Int){
        _isGettingListUserLoading.value = true
        val client = ApiConfig.getAuthenticationApiService().getListUser(page)
        client.enqueue(object: retrofit2.Callback<ListUserResponse>{
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                _isGettingListUserLoading.value = false
                if(response.isSuccessful){
                    _listUserResponse.value = response.body()
                    Log.d(TAG, "Successs")
                }else{
                    _isGettingListUserFail.value = true
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                _isGettingListUserLoading.value = false
                _isGettingListUserFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getListUserMore(page: Int){
        _isGettingListUserLoading.value = true
        val client = ApiConfig.getAuthenticationApiService().getListUser(page)
        client.enqueue(object: retrofit2.Callback<ListUserResponse>{
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                _isGettingListUserLoading.value = false
                if(response.isSuccessful){
                    _listUserResponse2.value = response.body()
                    Log.d(TAG, "Success")
                }else{
                    _isGettingListUserFail.value = true
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                _isGettingListUserLoading.value = false
                _isGettingListUserFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getListMeal(input: String){
        _isLoading.value = true
        val client = ApiConfig.getMealApiService().getListFood(input)
        client.enqueue(object: retrofit2.Callback<ListMealResponse>{
            override fun onResponse(
                call: Call<ListMealResponse>,
                response: Response<ListMealResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listMealResponse.value = response.body()
                    Log.d(TAG, "Success")
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListMealResponse>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getListMealMore(input: String){
        _isLoading.value = true
        val client = ApiConfig.getMealApiService().getListFood(input)
        client.enqueue(object: retrofit2.Callback<ListMealResponse>{
            override fun onResponse(
                call: Call<ListMealResponse>,
                response: Response<ListMealResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listMealResponse2.value = response.body()
                    Log.d(TAG, "Success")
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListMealResponse>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}