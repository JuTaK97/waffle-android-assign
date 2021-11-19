package com.example.assignment4.ui.seminar


// GET 세미나 했을 때 응답으로 받아오는 정보
data class Seminar (
    val id : Int,
    val name : String,
    val instructors: List<SeminarInstructor>,
    val participant_count : Int
)

data class SeminarInstructor (
    val id : Int,
    val username : String,
    val email : String,
    val first_name : String,
    val last_name : String,
    val joined_at : String
)