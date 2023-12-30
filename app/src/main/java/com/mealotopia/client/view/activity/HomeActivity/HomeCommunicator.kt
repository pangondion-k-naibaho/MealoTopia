package com.mealotopia.client.view.activity.HomeActivity

import com.mealotopia.client.model.data_class.meal.DetailMealResponse

interface HomeCommunicator {
    fun onListDisplayingLoadingStarted()
    fun onListDisplayingLoadingFinished()

    fun onDeliverMealToDetail(deliveredMeal: DetailMealResponse)
}