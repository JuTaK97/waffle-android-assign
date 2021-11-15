package com.example.assignment4.ui.seminar

import com.example.assignment4.dto.CreateSeminarFetch
import com.example.assignment4.dto.CreateSeminarRequest
import com.example.assignment4.dto.DetailSeminarFetch
import com.example.assignment4.ui.seminar.detail.Role
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SeminarService {
    @GET("api/v1/seminar/")
    fun getSeminarList() : Call<List<Seminar>>

    @POST("api/v1/seminar/")
    fun createSeminar(@Body param: CreateSeminarRequest) : Call<CreateSeminarFetch>

    @GET("api/v1/seminar/{seminar_id}/")
    fun getSeminarDetail(@Path(value="seminar_id") seminar_id:Int) : Call<DetailSeminarFetch>


    @POST("api/v1/seminar/{seminar_id}/user/")
    fun joinSeminar(@Path(value="seminar_id") seminar_id: Int, @Body role:Role)
    : Call<DetailSeminarFetch>
}