package com.example.assignment4.signup

import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.dto.*
import com.example.assignment4.ui.MainActivity
import com.example.assignment4.ui.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val retrofit: Retrofit
) :ViewModel(){
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val _role  = MutableLiveData<String>()
    val role : LiveData<String> = _role
    private val _result  = MutableLiveData<String>()
    val result : LiveData<String> = _result

    lateinit var errorMessage: String

    fun signUp(param:  RequestSignup) {
        val signupResponse = signUpRepository.signup(param)
        signupResponse.clone().enqueue(object : Callback<FetchSignup> {
            override fun onFailure(call: Call<FetchSignup>, t: Throwable) {
                Timber.d("Signup failed")
            }
            override fun onResponse(call: Call<FetchSignup>, response: Response<FetchSignup>) {
                if(!response.isSuccessful) {
                    if(response.errorBody()!=null) {
                        try {
                            val error = retrofit.responseBodyConverter<ErrorMessage>(
                                ErrorMessage::class.java,
                                ErrorMessage::class.java.annotations
                            ).convert(response.errorBody())
                            errorMessage = parsing(error)
                        } catch (e:Exception) {
                            errorMessage = response.errorBody()?.string()!!
                        }
                    }
                    else {
                        errorMessage = "Login failed"
                    }
                    _result.value = "fail"
                }
                else {
                    sharedPreferences.edit {
                        putString("token", response.body()!!.token)
                    }
                    _result.value = "success"
                }
            }
        })
    }
}