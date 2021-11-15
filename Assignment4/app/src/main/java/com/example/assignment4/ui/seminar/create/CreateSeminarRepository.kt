package com.example.assignment4.ui.seminar.create

import com.example.assignment4.ui.seminar.SeminarService
import com.example.assignment4.dto.CreateSeminarFetch
import com.example.assignment4.dto.CreateSeminarRequest
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CreateSeminarRepository @Inject constructor(
    private val seminarService : SeminarService
){
    fun createSeminar(param : CreateSeminarRequest) : Call<CreateSeminarFetch> {
        return seminarService.createSeminar(param)
    }


}