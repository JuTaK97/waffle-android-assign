package com.example.assignment4.signup

import com.example.assignment4.dto.FetchSignup
import com.example.assignment4.dto.RequestSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("api/v1/signup/")
    fun signup(@Body params: RequestSignup): Call<FetchSignup>
}