package com.example.assignment4.ui.seminar.create

import androidx.lifecycle.ViewModel
import com.example.assignment4.ui.seminar.create.dto.CreateSeminarFetch
import com.example.assignment4.ui.seminar.create.dto.CreateSeminarRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CreateSeminarViewModel @Inject constructor(
    private val createSeminarRepository: CreateSeminarRepository
) : ViewModel() {
    fun createSeminar(param : CreateSeminarRequest): Call<CreateSeminarFetch> {
        return  createSeminarRepository.createSeminar(param)
    }

}