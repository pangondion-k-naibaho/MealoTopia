package com.mealotopia.client.viewmodel

import android.support.v4.os.IResultReceiver._Parcel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mealotopia.client.model.data_class.LoginResponse
import com.mealotopia.client.model.networking.ApiConfig
import retrofit2.Call
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val TAG = LoginViewModel::class.java.simpleName

    private var _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse : LiveData<LoginResponse> = _loginResponse

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail : LiveData<Boolean> = _isFail

    fun loginUser(email: String, password: String){
        _isLoading.value = true
        val client = ApiConfig.getLoginApiService().loginUser(email, password)
        client.enqueue(object: retrofit2.Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _loginResponse.value = response.body()
                    Log.d(TAG, "Success")
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}