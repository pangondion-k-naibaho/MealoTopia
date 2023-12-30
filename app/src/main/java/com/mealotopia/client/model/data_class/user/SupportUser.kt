package com.mealotopia.client.model.data_class.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupportUser(
    var url: String = "",
    var text: String = ""
):Parcelable