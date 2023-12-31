package com.mealotopia.client.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mealotopia.client.model.data_class.meal.DetailMealResponse
import com.mealotopia.client.model.local.BookmarkedMealDao
import com.mealotopia.client.model.local.MealDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailnBookmarkViewModel(application: Application): AndroidViewModel(application) {
    private var bookmarkedMealDao: BookmarkedMealDao?= null
    private var mealDatabase: MealDatabase?= MealDatabase.getDatabase(application)

    init {
        bookmarkedMealDao = mealDatabase?.bookmarkedMealDao()
    }

    fun getBookmarkedMeal(): LiveData<List<DetailMealResponse>>{
        return bookmarkedMealDao?.getBookmarkedMeal()!!
    }

    fun addMealToBookmarkedMeal(data: DetailMealResponse){
        CoroutineScope(Dispatchers.IO).launch {
            bookmarkedMealDao?.addToListBookmarked(data)
        }
    }

    fun removeFromBookmarkedMeal(idMeal: String){
        CoroutineScope(Dispatchers.IO).launch {
            bookmarkedMealDao?.removeFromBookmarkedMeal(idMeal)
        }
    }

    fun isMealBookmarked(idMeal: String) = bookmarkedMealDao?.isMealBookmarked(idMeal)
}