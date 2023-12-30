package com.mealotopia.client.model.data_class.meal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealIngridients(
    var ingridientName: String?= null,
    var ingridientMeasure: String?= null
):Parcelable