package com.example.assignment4.ui.seminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment4.ui.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SeminarViewModel @Inject constructor(
    private val seminarService: SeminarService
) : ViewModel() {

    private val _seminarList = MutableLiveData<List<Seminar>>()
    val seminarList : LiveData<List<Seminar>> = _seminarList


    fun getSeminarList() {
        val response = seminarService.getSeminarList()
        response.enqueue(object : Callback<List<Seminar>>{
            override fun onFailure(call: Call<List<Seminar>>, t: Throwable) {
                Timber.d("Seminar GET failed")
            }
            override fun onResponse(call: Call<List<Seminar>>, response: Response<List<Seminar>>) {
                if(response.body()!=null) {
                    _seminarList.value = response.body()
                    Timber.d("Seminar GET success")
                }
            }
        })
    }

}