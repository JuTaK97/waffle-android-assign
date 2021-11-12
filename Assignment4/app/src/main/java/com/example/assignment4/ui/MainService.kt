package com.example.assignment4.ui

import com.example.assignment4.ui.user.User
import retrofit2.Call
import retrofit2.http.GET

interface MainService {
    @GET("api/v1/user/me/")
    fun getUserInfo() : Call<User>
}