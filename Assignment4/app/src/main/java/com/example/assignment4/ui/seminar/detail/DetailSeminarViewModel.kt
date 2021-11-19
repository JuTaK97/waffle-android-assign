package com.example.assignment4.ui.seminar.detail

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.dto.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.metadata.`ProtoBuf$QualifiedNameTable$QualifiedNameOrBuilder`

@HiltViewModel
class DetailSeminarViewModel @Inject constructor(
    private val detailSeminarRepository: DetailSeminarRepository,
    private val retrofit: Retrofit
) : ViewModel() {

    private val _seminarInfo = MutableLiveData<DetailSeminarFetch>()
    val seminarInfo : LiveData<DetailSeminarFetch> = _seminarInfo

    private val _joinResult = MutableLiveData<String>()
    val joinResult : LiveData<String> = _joinResult

    lateinit var loadResult : String
    lateinit var errorMessage : String

    fun getDetailSeminar(id: Int)  {
        val response = detailSeminarRepository.getSeminarDetail(id)
        response.clone().enqueue(object : Callback<DetailSeminarFetch> {
            override fun onFailure(call: Call<DetailSeminarFetch>, t: Throwable) {
                loadResult = "fail"
            }
            override fun onResponse(
                call: Call<DetailSeminarFetch>,
                response: Response<DetailSeminarFetch>
            ) {
                if(response.isSuccessful) {
                    _seminarInfo.value = response.body()
                    loadResult = "success"
                }
                else {
                    loadResult = "fail"
                }
            }
        })
    }
    fun joinSeminar(id : Int, role: String) {
        val response = detailSeminarRepository.joinSeminar(id, role)
        response.clone().enqueue(object : Callback<DetailSeminarFetch> {
            override fun onFailure(call: Call<DetailSeminarFetch>, t: Throwable) {
                _joinResult.value = "fail"
                errorMessage = "세미나 참가에 실패했습니다."
            }
            override fun onResponse(
                call: Call<DetailSeminarFetch>,
                response: Response<DetailSeminarFetch>
            ) {
                if(response.isSuccessful) {
                    errorMessage = "세미나에 참가하였습니다."
                    _joinResult.value = "success"
                    _seminarInfo.value = response.body()
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
                            errorMessage = response.errorBody()?.string()!!
                        }
                    }
                    else {
                        errorMessage = "세미나 참가에 실패했습니다."
                    }
                    _joinResult.value = "fail"
                }
            }
        })
    }
}