package com.example.assignment4.dto

// 로그인 PUT 했을 때 응답으로 받아오는 정보
data class FetchLogin (
    val success: Boolean,
    val token:String
)
