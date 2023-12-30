package com.mealotopia.client.view.activity.HomeActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ActivityHomeBinding
import com.mealotopia.client.databinding.DrawerHeaderLayoutBinding
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.EMAIL_KEY
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.TOKEN_KEY
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.TOKEN_PREFERENCES
import com.mealotopia.client.view.activity.HomeActivity.fragment.ListMealFragment
import com.mealotopia.client.view.activity.HomeActivity.fragment.ListUserFragment
import com.mealotopia.client.view.activity.HomeActivity.fragment.SavedMealFragment
import com.mealotopia.client.view.activity.LoginActivity.LoginActivity
import com.mealotopia.client.view.advanced_ui.PopupDialogListener
import com.mealotopia.client.view.advanced_ui.showPopupDialog
import com.mealotopia.client.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity(), HomeCommunicator {
    private lateinit var binding : ActivityHomeBinding
    private val TAG = HomeActivity::class.java.simpleName

    private lateinit var adapterFragmentDrawerNav: AdapterFragmentDrawerNav
    private var retrievedEmail: String?= null
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var sharedPreferences: SharedPreferences

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpView()
    }

    private fun setUpLoading(isLoading: Boolean){
        if(isLoading) binding.pbHome.visibility = View.VISIBLE else binding.pbHome.visibility = View.GONE
    }

    private fun setUpFailAction(isFail: Boolean){
        if(isFail){
            Toast.makeText(this@HomeActivity, "Failed to Log out", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpSuccessAction(isSuccess: Boolean){
        if(isSuccess){
            val editor = sharedPreferences.edit()
            editor.remove(TOKEN_KEY)
            editor.apply()
            this@HomeActivity.showPopupDialog(
                getString(R.string.tvSuccess_Logout),
                R.drawable.waitress_smile,
                object: PopupDialogListener{
                    override fun onClickListener() {
                        closeOptionsMenu()
                        startActivity(
                            LoginActivity.newIntent(this@HomeActivity)
                        )
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }

                }
            )
        }
    }

    private fun setUpView(){
        sharedPreferences = this@HomeActivity.getSharedPreferences(TOKEN_PREFERENCES, Context.MODE_PRIVATE)
        retrievedEmail = sharedPreferences.getString(EMAIL_KEY, null)

        homeViewModel.isLoading.observe(this@HomeActivity, {
            setUpLoading(it)
        })

        homeViewModel.isFail.observe(this@HomeActivity, {
            setUpFailAction(it)
        })

        homeViewModel.isSuccess.observe(this@HomeActivity, {
            setUpSuccessAction(it)
        })

        val listMealFragment = ListMealFragment.newInstance("List Meal Fragment")
        val listUserFragment = ListUserFragment.newInstance("List User Fragment")
        val savedMealFragment = SavedMealFragment.newInstance("Saved Meal Fragment")

        binding.homeToolbar.navigationIcon = ContextCompat.getDrawable(this@HomeActivity, R.drawable.ic_menu_white)

        val toggle = ActionBarDrawerToggle(
            this@HomeActivity, binding.drawerLayoutHome, binding.homeToolbar, 0, 0
        )

        binding.drawerLayoutHome.addDrawerListener(toggle)
        toggle.syncState()

        binding.homeNavView.apply {
            val headerBinding = getHeaderView(0)
            val emailText = headerBinding.findViewById<TextView>(R.id.tvUserEmail)

            emailText.text = retrievedEmail

            replaceFragment(listMealFragment)
            setCheckedItem(R.id.menu_listMeal)
            setNavigationItemSelectedListener { menuItem ->
                when(menuItem.itemId){
                    R.id.menu_listMeal -> replaceFragment(listMealFragment)
                    R.id.menu_listUser -> replaceFragment(listUserFragment)
                    R.id.menu_listSavedMeal -> replaceFragment(savedMealFragment)
                    R.id.menu_logout -> {
                        this@HomeActivity.showPopupDialog(
                            getString(R.string.tvWarning_Logout),
                            R.drawable.wondering,
                            object: PopupDialogListener{
                                override fun onClickListener() {
                                    homeViewModel.logOutUser()
                                }
                            }
                        )
                    }
                    else -> {}
                }
                binding.drawerLayoutHome.closeDrawers()
                menuItem.isChecked = true
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Replace the content of the FrameLayout with the new fragment
        transaction.replace(R.id.fragmentContainer, fragment)

        // Add the transaction to the back stack (optional)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        retrievedEmail = null
    }

    override fun onListUserLoadingStarted() {
        setUpLoading(true)
    }

    override fun onListUserLoadingFinished() {
        setUpLoading(false)
    }


//    override fun sendStatusLoadingToHome(isLoading: Boolean) {
//        if(isLoading) setUpLoading(isLoading)
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            setUpLoading(!isLoading)
//        },5000)
//    }
}