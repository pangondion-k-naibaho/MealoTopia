package com.mealotopia.client.model.data_class.meal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMealResponse(
    var meals: List<DetailMealResponse>?= null
):Parcelable