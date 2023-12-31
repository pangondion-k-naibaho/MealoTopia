package com.mealotopia.client.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mealotopia.client.model.data_class.meal.DetailMealResponse

@Dao
interface BookmarkedMealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToListBookmarked(preBookmarkedMeal: DetailMealResponse)

    @Query("SELECT * FROM Bookmarked_Meal")
    fun getBookmarkedMeal(): LiveData<List<DetailMealResponse>>

    @Query("DELETE FROM Bookmarked_Meal WHERE Bookmarked_meal.idMeal = :idMeal")
    fun removeFromBookmarkedMeal(idMeal: String): Int

    @Query("SELECT COUNT(*) FROM Bookmarked_Meal WHERE Bookmarked_Meal.idMeal = :idMeal")
    fun isMealBookmarked(idMeal: String): Int

}