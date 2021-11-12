package com.example.assignment4.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RequestSignup(

    val email: String,
    val username: String,
    val password:String,
    val role:String
)
