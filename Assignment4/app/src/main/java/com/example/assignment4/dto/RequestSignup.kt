package com.example.assignment4.dto

// 회원가입 POST 할 때 보내는 정보
data class RequestSignup(

    val email: String,
    val username: String,
    val password:String,
    val role:String
)
