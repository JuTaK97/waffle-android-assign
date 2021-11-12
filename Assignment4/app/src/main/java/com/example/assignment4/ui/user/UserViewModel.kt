package com.example.assignment4.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.ui.seminar.Seminar
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo : LiveData<User> = _userInfo

    fun getUserInfo() {
        val response = userService.getUserInfo()
        response.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Timber.d("Failed to get user info")
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                _userInfo.value = response.body()
            }
        })
    }


}