package com.mealotopia.client.model.networking

import com.mealotopia.client.model.data_class.meal.ListMealResponse
import com.mealotopia.client.model.data_class.user.ListUserResponse
import com.mealotopia.client.model.data_class.user.LoginResponse
import com.mealotopia.client.view.activity.HomeActivity.fragment.ListMealFragment
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginApiService {

    @FormUrlEncoded
    @POST("api/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("api/logout")
    fun logoutUser(): Call<Void>

    @GET("api/users")
    fun getListUser(
        @Query("page") page: Int
    ): Call<ListUserResponse>

}

interface MealApiService{
    @GET("search.php")
    fun getListFood(
        @Query("s") search: String
    ): Call<ListMealResponse>

}