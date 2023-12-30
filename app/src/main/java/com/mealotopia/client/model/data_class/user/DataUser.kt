package com.mealotopia.client.model.data_class.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUser(
    var id: Int = 0,
    var email: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var avatar: String = ""
):Parcelable