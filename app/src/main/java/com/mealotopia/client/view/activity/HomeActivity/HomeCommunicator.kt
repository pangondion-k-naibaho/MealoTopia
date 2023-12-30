package com.mealotopia.client.view.activity.HomeActivity

interface HomeCommunicator {
//    fun sendStatusLoadingToHome(isLoading: Boolean)

    fun onListUserLoadingStarted()
    fun onListUserLoadingFinished()

    fun sendStatusFailToHome(isFail: Boolean, message: String){}
}