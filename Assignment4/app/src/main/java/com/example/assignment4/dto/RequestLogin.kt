package com.example.assignment4.dto


// 로그인 PUT 할때 보내는 정보
data class RequestLogin(

    val email: String,
    val password: String
)