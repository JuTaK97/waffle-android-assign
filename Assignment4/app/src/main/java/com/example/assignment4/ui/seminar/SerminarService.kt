package com.example.assignment4.ui.seminar

import com.example.assignment4.ui.user.User
import retrofit2.Call
import retrofit2.http.GET

interface SeminarService {
    @GET("api/v1/seminar/")
    fun getSeminarList() : Call<List<Seminar>>

    @GET("api/v1/user/me/")
    fun getRole() : Call<User>
}