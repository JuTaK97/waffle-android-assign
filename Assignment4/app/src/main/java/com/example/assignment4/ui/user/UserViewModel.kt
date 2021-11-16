package com.example.assignment4.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.dto.ErrorMessage
import com.example.assignment4.dto.parsing
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userService: UserService,
    private val retrofit: Retrofit
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo : LiveData<User> = _userInfo

    private val _resultMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _resultMessage

    fun getUserInfo() {
        val response = userService.getUserInfo()
        response.clone().enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Timber.d("Failed to get user info")
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.body()!=null) {
                    Timber.d("GET user info success")
                    _userInfo.value = response.body()
                }
                else {
                    Timber.d("GET user info unsuccessful")
                }

            }
        })
    }
    fun editUserInfo(param : UserInfoEditRequest) {
        val response = userService.editUserInfo(param)
        response.clone().enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Timber.d("Failed to edit user info")
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(!response.isSuccessful) {
                    if(response.errorBody()!=null) {
                        try {
                            val error = retrofit.responseBodyConverter<ErrorMessage>(
                                ErrorMessage::class.java,
                                ErrorMessage::class.java.annotations
                            ).convert(response.errorBody())
                            _resultMessage.value = parsing(error)
                            Timber.d("aaaaaaaaaaaaaaaaaaaaa")
                            Timber.d(_resultMessage.value)
                        } catch (e: Exception) {
                            _resultMessage.value = response.errorBody()?.string()!!
                        }
                    }
                    else {
                        _resultMessage.value = "Edit failed"   // null이 올 상황이 있을까
                    }
                }
                else {
                    _resultMessage.value = "회원정보가 수정되었습니다."
                }

            }
        })
    }


}