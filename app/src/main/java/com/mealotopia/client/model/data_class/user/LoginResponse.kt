package com.mealotopia.client.model.data_class.user

data class LoginResponse(
    var token: String = "",
    var error: String = ""
)