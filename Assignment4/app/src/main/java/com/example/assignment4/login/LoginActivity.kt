package com.example.assignment4.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
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
import androidx.activity.R
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.edit
import com.example.assignment4.ui.MainActivity
import com.example.assignment4.signup.SignUpActivity
import com.example.assignment4.ui.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            setContentView(com.example.assignment4.R.layout.activity_login_auto)
            Timber.d("token exists")
            viewModel.getRole()
            viewModel.role.observe(this, {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("role", it)
                startActivity(intent)
                finish()
            })
        }
        else {
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val resultListener =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
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
                viewModel.login(param)
            }
            viewModel.result.observe(this, {
                if(it == "fail") {
                    Toast.makeText(this, viewModel.errorMessage, Toast.LENGTH_LONG).show()
                }
                else {
                    viewModel.getRole()
                }
            })
            viewModel.role.observe(this, {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("role", it)
                startActivity(intent)
                finish()
            })
        }
    }

}
