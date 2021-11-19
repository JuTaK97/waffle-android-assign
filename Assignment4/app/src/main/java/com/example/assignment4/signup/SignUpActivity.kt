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
            viewModel.signUp(param)
        }
        viewModel.result.observe(this, {
            if(it == "fail") {
                Toast.makeText(this, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
            }
            else {
                sharedPreferences.edit{
                    putString("role", binding.signupRole.text.toString())
                }
                Toast.makeText(this,"회원가입에 성공하였습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("role", binding.signupRole.text.toString() )
                startActivity(intent)
                setResult(RESULT_OK, Intent())
                finish()
            }
        })
    }
}