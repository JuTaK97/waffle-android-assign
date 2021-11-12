package com.example.assignment4.login

import com.example.assignment4.dto.*
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @PUT("api/v1/login/")
    fun login(@Body params: RequestLogin) : Call<FetchLogin>

    //임시
    @GET("api/v1/user/me/")
    fun get() : Call<FetchUserInfo>
}