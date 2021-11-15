package com.example.assignment4.login

import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment4.dto.*
import com.example.assignment4.ui.MainActivity
import com.example.assignment4.ui.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val _role  = MutableLiveData<String>()
    val role : LiveData<String> = _role
    private val _result  = MutableLiveData<String>()
    val result : LiveData<String> = _result

    lateinit var errorMessage: String

    fun login(param: RequestLogin) {
        val loginResponse = loginRepository.login(param)
        loginResponse.clone().enqueue(object : Callback<FetchLogin>{
            override fun onFailure(call: Call<FetchLogin>, t: Throwable) {
                Timber.d("Login failed")
                errorMessage = "Login failed"
                _result.value = "fail"
            }
            override fun onResponse(call: Call<FetchLogin>, response: Response<FetchLogin>) {
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
        val roleResponse = loginRepository.getRole()
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