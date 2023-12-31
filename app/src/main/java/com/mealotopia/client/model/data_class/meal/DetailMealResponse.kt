package com.mealotopia.client.model.data_class.meal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Bookmarked_Meal")
data class DetailMealResponse(
    @field:SerializedName("idMeal")
    @PrimaryKey(autoGenerate = false)
    var idMeal: String = "",

    @field:SerializedName("strMeal")
    var strMeal: String?= null,

    @field:SerializedName("strDrinkAlternate")
    var strDrinkAlternate: String?= null,

    @field:SerializedName("strCategory")
    var strCategory: String?= null,

    @field:SerializedName("strArea")
    var strArea: String?= null,

    @field:SerializedName("strInstructions")
    var strInstructions: String?= null,

    @field:SerializedName("strMealThumb")
    var strMealThumb: String?= null,

    @field:SerializedName("strTags")
    var strTags: String?= null,

    @field:SerializedName("strYoutube")
    var strYoutube: String?= null,

    @field:SerializedName("strIngredient1")
    var strIngredient1: String?= null,

    @field:SerializedName("strIngredient2")
    var strIngredient2: String?= null,

    @field:SerializedName("strIngredient3")
    var strIngredient3: String?= null,

    @field:SerializedName("strIngredient4")
    var strIngredient4: String?= null,

    @field:SerializedName("strIngredient5")
    var strIngredient5: String?= null,

    @field:SerializedName("strIngredient6")
    var strIngredient6: String?= null,

    @field:SerializedName("strIngredient7")
    var strIngredient7: String?= null,

    @field:SerializedName("strIngredient8")
    var strIngredient8: String?= null,

    @field:SerializedName("strIngredient9")
    var strIngredient9: String?= null,

    @field:SerializedName("strIngredient10")
    var strIngredient10: String?= null,

    @field:SerializedName("strIngredient11")
    var strIngredient11: String?= null,

    @field:SerializedName("strIngredient12")
    var strIngredient12: String?= null,

    @field:SerializedName("strIngredient13")
    var strIngredient13: String?= null,

    @field:SerializedName("strIngredient14")
    var strIngredient14: String?= null,

    @field:SerializedName("strIngredient15")
    var strIngredient15: String?= null,

    @field:SerializedName("strIngredient16")
    var strIngredient16: String?= null,

    @field:SerializedName("strIngredient17")
    var strIngredient17: String?= null,

    @field:SerializedName("strIngredient18")
    var strIngredient18: String?= null,

    @field:SerializedName("strIngredient19")
    var strIngredient19: String?= null,

    @field:SerializedName("strIngredient20")
    var strIngredient20: String?= null,

    @field:SerializedName("strMeasure1")
    var strMeasure1: String?= null,

    @field:SerializedName("strMeasure2")
    var strMeasure2: String?= null,

    @field:SerializedName("strMeasure3")
    var strMeasure3: String?= null,

    @field:SerializedName("strMeasure4")
    var strMeasure4: String?= null,

    @field:SerializedName("strMeasure5")
    var strMeasure5: String?= null,

    @field:SerializedName("strMeasure6")
    var strMeasure6: String?= null,

    @field:SerializedName("strMeasure7")
    var strMeasure7: String?= null,

    @field:SerializedName("strMeasure8")
    var strMeasure8: String?= null,

    @field:SerializedName("strMeasure9")
    var strMeasure9: String?= null,

    @field:SerializedName("strMeasure10")
    var strMeasure10: String?= null,

    @field:SerializedName("strMeasure11")
    var strMeasure11: String?= null,

    @field:SerializedName("strMeasure12")
    var strMeasure12: String?= null,

    @field:SerializedName("strMeasure13")
    var strMeasure13: String?= null,

    @field:SerializedName("strMeasure14")
    var strMeasure14: String?= null,

    @field:SerializedName("strMeasure15")
    var strMeasure15: String?= null,

    @field:SerializedName("strMeasure16")
    var strMeasure16: String?= null,

    @field:SerializedName("strMeasure17")
    var strMeasure17: String?= null,

    @field:SerializedName("strMeasure18")
    var strMeasure18: String?= null,

    @field:SerializedName("strMeasure19")
    var strMeasure19: String?= null,

    @field:SerializedName("strMeasure20")
    var strMeasure20: String?= null,

    @field:SerializedName("strSource")
    var strSource: String?= null,

    @field:SerializedName("strImageSource")
    var strImageSource: String?= null,

    @field:SerializedName("strCreativeCommonsConfirmed")
    var strCreativeCommonsConfirmed: String?= null,

    @field:SerializedName("dateModified")
    var dateModified: String?= null,
):Parcelable