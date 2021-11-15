package com.example.assignment4.ui.seminar.detail

import com.example.assignment4.ui.seminar.SeminarService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DetailSeminarRepository @Inject constructor(
    private val seminarService: SeminarService
) {
        fun getSeminarDetail(id : Int) : Call<DetailSeminarFetch> {
            return seminarService.getSeminarDetail(id)
        }
        fun joinSeminar(id : Int, role:String) : Call<DetailSeminarFetch> {
            val roleData = Role(role)
            return seminarService.joinSeminar(id, roleData)
        }
}

data class Role (
    val role : String
)