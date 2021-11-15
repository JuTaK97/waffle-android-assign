package com.example.assignment4.signup

import com.example.assignment4.dto.FetchSignup
import com.example.assignment4.dto.RequestSignup
import com.example.assignment4.ui.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignUpService {
    @POST("api/v1/signup/")
    fun signup(@Body params: RequestSignup): Call<FetchSignup>

    @GET("api/v1/user/me/")
    fun get() :  Call<User>
}