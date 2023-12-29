package com.mealotopia.client.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mealotopia.client.model.networking.ApiConfig
import retrofit2.Call
import retrofit2.Response

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
}