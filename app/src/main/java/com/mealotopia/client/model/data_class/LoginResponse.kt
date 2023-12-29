package com.mealotopia.client.model.data_class

data class LoginResponse(
    var token: String = "",
    var error: String = ""
)