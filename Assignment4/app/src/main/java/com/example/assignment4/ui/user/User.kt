package com.example.assignment4.ui.user

// GET "api/v1/user/me/"의 응답으로 사용됨
// PUT "api/v1/user/me/" 의 응답으로 사용됨
data class User (
    val id : Int,
    val username : String,
    val email : String,
    val last_login : String,
    val date_joined : String,
    val first_name : String,
    val last_name : String,
    val participant: Participant?,
    val instructor: Instructor?
)

data class Participant (
    val id : Int,
    val university : String,
    val accepted : Boolean,
    val seminars : List<MySeminar>
)

data class MySeminar (
    val id : Int,
    val name : String,
    val joined_at: String,
    val is_active : Boolean,
    val dropped_at : String?
)

data class Instructor (
    val id : Int,
    val company : String,
    val year : Int?,
    val charge : Charge?
)

data class Charge (
    val id : Int,
    val name : String,
    val joined_at : String
)
