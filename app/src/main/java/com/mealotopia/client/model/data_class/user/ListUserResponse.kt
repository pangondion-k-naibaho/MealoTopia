package com.mealotopia.client.model.data_class.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListUserResponse(
    var page: Int = 0,
    var per_page: Int = 0,
    var total: Int = 0,
    var total_pages: Int = 2,
    var data: List<DataUser>?= null,
    var support: SupportUser?= null
):Parcelable