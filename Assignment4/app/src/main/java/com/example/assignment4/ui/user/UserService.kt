package com.example.assignment4.ui.user

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("api/v1/user/me/")
    fun getUserInfo() : Call<User>
}