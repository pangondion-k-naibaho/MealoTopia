package com.mealotopia.client.model.networking

import com.mealotopia.client.model.data_class.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiService {

    @FormUrlEncoded
    @POST("api/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("api/logout")
    fun logoutUser(): Call<Void>

}

interface MealApiService{

}