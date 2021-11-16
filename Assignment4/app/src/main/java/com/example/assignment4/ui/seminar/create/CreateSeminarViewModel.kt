package com.example.assignment4.ui.seminar.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.dto.CreateSeminarFetch
import com.example.assignment4.dto.CreateSeminarRequest
import com.example.assignment4.dto.ErrorMessage
import com.example.assignment4.dto.parsing
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CreateSeminarViewModel @Inject constructor(
    private val createSeminarRepository: CreateSeminarRepository,
    private val retrofit: Retrofit
) : ViewModel() {

    private val _result  = MutableLiveData<String>()
    val result : LiveData<String> = _result

    private val _id  = MutableLiveData<Int>()
    val id : LiveData<Int> = _id

    lateinit var errorMessage: String

    fun createSeminar(param : CreateSeminarRequest) {
        val response = createSeminarRepository.createSeminar(param)
        response.enqueue(object : Callback<CreateSeminarFetch> {
            override fun onFailure(call: Call<CreateSeminarFetch>, t: Throwable) {
                _result.value = "fail"
                errorMessage = "세미나 생성에 실패하였습니다."
            }
            override fun onResponse(
                call: Call<CreateSeminarFetch>,
                response: Response<CreateSeminarFetch>
            ) {
                if(response.isSuccessful) {
                    _id.value = response.body()!!.id!!.toInt()
                    _result.value = "success"
                }
                else {
                    if(response.errorBody()!=null) {
                        try {
                            val error = retrofit.responseBodyConverter<ErrorMessage>(
                                ErrorMessage::class.java,
                                ErrorMessage::class.java.annotations
                            ).convert(response.errorBody())
                            errorMessage = parsing(error)
                        } catch (e: Exception) {
                            errorMessage = "세미나가 생성되지 않았습니다. 입력값을 확인해주세요."
                        }
                    }
                    else {
                        errorMessage = "Login failed"
                    }
                    _result.value = "fail"
                }
            }
        })
    }

}