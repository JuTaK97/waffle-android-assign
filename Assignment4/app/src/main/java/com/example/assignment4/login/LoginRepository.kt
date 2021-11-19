package com.example.assignment4.login

import com.example.assignment4.dto.*
import com.example.assignment4.ui.user.User
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginRepository @Inject constructor(private val loginService: LoginService){

    fun login(param:RequestLogin) : Call<FetchLogin> {
        return loginService.login(param)
    }
    fun getRole() : Call<User> {
        return loginService.get()
    }

}