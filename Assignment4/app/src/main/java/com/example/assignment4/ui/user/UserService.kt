package com.example.assignment4.ui.user

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserService {
    @GET("api/v1/user/me/")
    fun getUserInfo() : Call<User>

    @PUT("api/v1/user/me/")
    fun editUserInfo(@Body param : UserInfoEditRequest) : Call<User>
}