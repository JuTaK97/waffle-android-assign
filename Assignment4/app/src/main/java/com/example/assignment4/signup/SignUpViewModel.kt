package com.example.assignment4.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.dto.FetchSignup
import com.example.assignment4.dto.RequestSignup
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) :ViewModel(){

    fun signUp(param: RequestSignup):Call<FetchSignup> {
        return signUpRepository.signup(param)
    }
}