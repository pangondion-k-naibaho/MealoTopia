package com.mealotopia.client.model.data_class.meal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMealResponse(
    var idMeal: String = "",
    var strMeal: String = "",
    var strDrinkAlternate: String?= null,
    var strCategory: String?= null,
    var strArea: String?= null,
    var strInstructions: String = "",
    var strMealThumb: String ="",
    var strTags: String = "",
    var strYoutube: String= "",
    var strIngridient1: String = "",
    var strIngridient2: String = "",
    var strIngridient3: String = "",
    var strIngridient4: String = "",
    var strIngridient5: String = "",
    var strIngridient6: String = "",
    var strIngridient7: String = "",
    var strIngridient8: String = "",
    var strIngridient9: String = "",
    var strIngridient10: String = "",
    var strIngridient11: String = "",
    var strIngridient12: String = "",
    var strIngridient13: String = "",
    var strIngridient14: String = "",
    var strIngridient15: String = "",
    var strIngridient16: String = "",
    var strIngridient17: String = "",
    var strIngridient18: String = "",
    var strIngridient19: String = "",
    var strIngridient20: String = "",
    var strMeasure1: String = "",
    var strMeasure2: String = "",
    var strMeasure3: String = "",
    var strMeasure4: String = "",
    var strMeasure5: String = "",
    var strMeasure6: String = "",
    var strMeasure7: String = "",
    var strMeasure8: String = "",
    var strMeasure9: String = "",
    var strMeasure10: String = "",
    var strMeasure11: String = "",
    var strMeasure12: String = "",
    var strMeasure13: String = "",
    var strMeasure14: String = "",
    var strMeasure15: String = "",
    var strMeasure16: String = "",
    var strMeasure17: String = "",
    var strMeasure18: String = "",
    var strMeasure19: String = "",
    var strMeasure20: String = "",
    var strSource: String = "",
    var strImageSource: String?= null,
    var strCreativeCommonsConfirmed: String?= null,
    var dateModified: String?= null,
):Parcelable