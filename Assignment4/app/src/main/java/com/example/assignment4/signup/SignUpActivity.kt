package com.example.assignment4.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.assignment4.ui.MainActivity
import com.example.assignment4.databinding.ActivitySignupBinding
import com.example.assignment4.dto.FetchSignup
import com.example.assignment4.dto.RequestSignup
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private val viewModel: SignUpViewModel by viewModels()
    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupButton.setOnClickListener {
            val param = RequestSignup(
                binding.signupEmail.text.toString(),
                binding.signupUsername.text.toString(),
                binding.signupPassword.text.toString(),
                binding.signupRole.text.toString()
            )
            val response = viewModel.signUp(param)
            response.enqueue(object: Callback<FetchSignup> {
                override fun onFailure(call: Call<FetchSignup>, t: Throwable) {
                    Timber.d("signup fail")
                    Toast.makeText(this@SignUpActivity, "Signup failed!!",Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<FetchSignup>, response: Response<FetchSignup>) {
                    if(response.body()?.token != null) {
                        Timber.d("signup success")
                        sharedPreferences.edit {
                            putString("token", response.body()!!.token)
                        }
                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                        val returnIntent = Intent()
                        setResult(RESULT_OK, returnIntent)
                        finish()
                    }
                }
            })

        }
    }
}