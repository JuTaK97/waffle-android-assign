package com.example.assignment4.signup

import com.example.assignment4.dto.FetchSignup
import com.example.assignment4.dto.RequestSignup
import com.example.assignment4.login.LoginService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpRepository @Inject constructor(private val signupService: SignUpService){
    fun signup(param: RequestSignup): Call<FetchSignup> {
        return signupService.signup(param)
    }
}