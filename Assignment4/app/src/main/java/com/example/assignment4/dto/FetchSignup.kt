package com.example.assignment4.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// 회원가입 POST 했을 때 응답으로 받아오는 정보
data class FetchSignup (
    val user:String,
    val token:String
)


