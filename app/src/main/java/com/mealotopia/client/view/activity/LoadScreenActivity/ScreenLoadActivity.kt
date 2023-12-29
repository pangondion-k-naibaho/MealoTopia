package com.mealotopia.client.view.activity.LoadScreenActivity

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ActivityScreenLoadBinding
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.TOKEN_KEY
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.TOKEN_PREFERENCES
import com.mealotopia.client.view.activity.HomeActivity.HomeActivity
import com.mealotopia.client.view.activity.LoginActivity.LoginActivity

class ScreenLoadActivity : Activity() {
    private val TAG = ScreenLoadActivity::class.java.simpleName
    private lateinit var binding: ActivityScreenLoadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{false}
        actionBar?.hide()
        setUpView()
    }

    private fun setUpView(){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object: Runnable{
            override fun run() {
                val sharedPreferences = this@ScreenLoadActivity.getSharedPreferences(TOKEN_PREFERENCES, Context.MODE_PRIVATE)

                val retrievedToken = sharedPreferences.getString(TOKEN_KEY, null)
                Log.d(TAG, "retrievedToken: $retrievedToken")

                if(retrievedToken == null){
                    startActivity(
                        LoginActivity.newIntent(this@ScreenLoadActivity)
                    )
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }else{
                    startActivity(
                        HomeActivity.newIntent(this@ScreenLoadActivity)
                    )
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }

            }

        }, 5000)
    }
}