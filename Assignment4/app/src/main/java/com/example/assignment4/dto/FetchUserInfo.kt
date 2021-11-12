package com.example.assignment4.dto

data class FetchUserInfo (
    val id : Int?,
    val username : String,
    val email : String,
    val last_login : String?,
    val date_joined : String?,
    val first_name : String?,
    val last_name : String?,
    val participant : Participant,
    val instructor : String?,
)
data class Participant (
    val id: Int,
    val university : String,
    val accepted :Boolean,
    val seminars : List<String>
)
