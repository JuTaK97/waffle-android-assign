package com.example.assignment4.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class FetchSignup (
    val user:String,
    val token:String
)


