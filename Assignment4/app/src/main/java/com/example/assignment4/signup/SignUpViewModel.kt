package com.example.assignment4.signup

import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.dto.FetchLogin
import com.example.assignment4.dto.FetchSignup
import com.example.assignment4.dto.RequestLogin
import com.example.assignment4.dto.RequestSignup
import com.example.assignment4.ui.MainActivity
import com.example.assignment4.ui.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
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
                    errorMessage = response.errorBody().toString()
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
    fun getRole() {
        val roleResponse = signUpRepository.getRole()
        roleResponse.clone().enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Timber.d("user role GET failed")
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body()?.instructor != null) {
                    _role.value = "instructor"
                } else if (response.body()?.participant != null) {
                    _role.value = "participant"
                } else {
                    _role.value = "other"
                }
            }
        })
    }
}