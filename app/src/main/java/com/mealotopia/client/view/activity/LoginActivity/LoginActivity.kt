package com.mealotopia.client.view.activity.LoginActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ActivityMainBinding
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.EMAIL_KEY
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.TOKEN_KEY
import com.mealotopia.client.model.Constants.PREFERENCES.Companion.TOKEN_PREFERENCES
import com.mealotopia.client.model.data_class.LoginResponse
import com.mealotopia.client.view.activity.HomeActivity.HomeActivity
import com.mealotopia.client.view.advanced_ui.PopupDialogListener
import com.mealotopia.client.view.advanced_ui.showPopupDialog
import com.mealotopia.client.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var binding : ActivityMainBinding

    private var emailRetrieved: String?= null
    private var passwordRetrieved: String?= null

    private val loginViewModel by viewModels<LoginViewModel>()

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()
        setUpView()
    }

    private fun setUpView(){
        binding.etEmailLogin.apply {
            addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emailRetrieved = s.toString()
                    if(s.toString().contains('@') == false){
                        binding.etEmailLogin.background = ContextCompat.getDrawable(this@LoginActivity, R.drawable.bg_roundedsquare_red)
                        binding.tvWarning.text = getString(R.string.tvWarning_Email)
                        binding.tvWarning.visibility = View.VISIBLE
                    }else{
                        binding.etEmailLogin.background = ContextCompat.getDrawable(this@LoginActivity, R.drawable.bg_primary)
                        binding.tvWarning.visibility = View.GONE
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }

        binding.etPasswordLogin.apply {
            addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    passwordRetrieved = s.toString()
                    binding.etPasswordLogin.background = ContextCompat.getDrawable(this@LoginActivity, R.drawable.bg_primary)
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }

        binding.btnLogin.setOnClickListener {
//            val email = binding.etEmailLogin.text.trim().toString()
//            val password = binding.etPasswordLogin.text.trim().toString()
            Log.d(TAG, "email: ${emailRetrieved}\npassword: ${passwordRetrieved}")
            loginViewModel.loginUser(emailRetrieved!!, passwordRetrieved!!)
        }

        loginViewModel.isLoading.observe(this@LoginActivity, {
            setUpLoading(it)
        })

        loginViewModel.loginResponse.observe(this@LoginActivity, {response->
            setUpSuccessAction(response)
        })

        loginViewModel.isFail.observe(this@LoginActivity, {
            setUpFailAction(it)
        })
    }

    private fun setUpLoading(isLoading: Boolean){
        if(isLoading) binding.pbLogin.visibility = View.VISIBLE else binding.pbLogin.visibility = View.GONE
    }

    private fun setUpSuccessAction(response: LoginResponse){
        Log.d(TAG, "response: $response")
        val sharedPreferences = this@LoginActivity.getSharedPreferences(TOKEN_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_KEY, response.token)
        editor.putString(EMAIL_KEY, emailRetrieved)
        editor.apply()
        editor.apply{
            Log.d(TAG, "isCommit: ${commit()}")
            if(commit()){
                this@LoginActivity.showPopupDialog(
                    getString(R.string.tvPopupDescSuccess),
                    R.drawable.waitress_smile,
                    object: PopupDialogListener{
                        override fun onClickListener() {
                            this@LoginActivity.closeOptionsMenu()
                            startActivity(
                                HomeActivity.newIntent(this@LoginActivity)
                            )
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        }
                    }
                )
            }
        }
    }

    private fun setUpFailAction(isFail: Boolean){
        Log.d(TAG, "isFail: $isFail")
        this@LoginActivity.showPopupDialog(
            getString(R.string.tvPopupDescWarning),
            R.drawable.waitress_confuse,
            object: PopupDialogListener{
                override fun onClickListener() {
                    this@LoginActivity.closeOptionsMenu()
                    binding.etEmailLogin.background = ContextCompat.getDrawable(this@LoginActivity, R.drawable.bg_roundedsquare_red)
                    binding.etPasswordLogin.background = ContextCompat.getDrawable(this@LoginActivity, R.drawable.bg_roundedsquare_red)
                }

            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        emailRetrieved = null
        passwordRetrieved = null
    }
}