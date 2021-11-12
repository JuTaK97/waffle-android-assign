package com.example.assignment4.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment4.databinding.ActivityLoginBinding
import com.example.assignment4.dto.*
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.edit
import com.example.assignment4.ui.MainActivity
import com.example.assignment4.signup.SignUpActivity

import org.json.JSONObject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(sharedPreferences.contains("token")) {
            Timber.d("token exists")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultListener = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode== RESULT_OK) {
                finish()
            }
        }
        binding.goSignupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultListener.launch(intent)
        }
        binding.loginButton.setOnClickListener {
            val param = RequestLogin(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            )
            val response = viewModel.login(param)
            response.enqueue(object: Callback<FetchLogin>{
                override fun onFailure(call: Call<FetchLogin>, t: Throwable) {
                    Timber.d("login fail")
                    Toast.makeText(this@LoginActivity,
                    "Login failed!!", Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<FetchLogin>, response: Response<FetchLogin>) {
                    if(!response.isSuccessful) {
                        val error = JSONObject(response.errorBody()!!.string())
                        if(error.has("email")) {
                            Toast.makeText(this@LoginActivity,
                                error.getString("email"), Toast.LENGTH_LONG).show()
                        }
                        if(error.has("detail")) {
                            Toast.makeText(this@LoginActivity,
                                error.getString("detail"), Toast.LENGTH_LONG).show()
                        }
                        if(error.has("non_field_errors")) {
                            Toast.makeText(this@LoginActivity,
                                error.getString("non_field_errors"), Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        Timber.d("login success")
                        Timber.d(response.body()!!.token)
                        sharedPreferences.edit {
                            putString("token", response.body()!!.token)
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            })
        }

    }

}
