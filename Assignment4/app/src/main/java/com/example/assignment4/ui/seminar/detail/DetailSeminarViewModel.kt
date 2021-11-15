package com.example.assignment4.ui.seminar.detail

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailSeminarViewModel @Inject constructor(
    private val detailSeminarRepository: DetailSeminarRepository
) : ViewModel() {

    private val _instructorList = MutableLiveData<List<DetailInstructor>>()
    val instructorList : LiveData<List<DetailInstructor>> = _instructorList

    private val _participantList = MutableLiveData<List<DetailParticipant>>()
    val participantList : LiveData<List<DetailParticipant>> = _participantList

    fun getDetailSeminar(id: Int) : Call<DetailSeminarFetch> {
        val response = detailSeminarRepository.getSeminarDetail(id)
        response.clone().enqueue(object : Callback<DetailSeminarFetch> {
            override fun onFailure(call: Call<DetailSeminarFetch>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<DetailSeminarFetch>,
                response: Response<DetailSeminarFetch>
            ) {
                _instructorList.value = response.body()!!.instructors
                _participantList.value = response.body()!!.participants
            }
        })
        return  response
    }
    fun joinSeminar(id : Int, role: String) :Call<DetailSeminarFetch> {
        Timber.d("aaaaaaaaaaaaaaaaaa")
        Timber.d(role)
        val response = detailSeminarRepository.joinSeminar(id, role)
        response.clone().enqueue(object : Callback<DetailSeminarFetch> {
            override fun onFailure(call: Call<DetailSeminarFetch>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<DetailSeminarFetch>,
                response: Response<DetailSeminarFetch>
            ) {
                if(response.isSuccessful) {
                    _instructorList.value = response.body()!!.instructors
                    _participantList.value = response.body()!!.participants
                }
            }
        })
        return  response
    }
}