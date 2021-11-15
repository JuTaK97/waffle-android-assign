package com.example.assignment4.dto

import com.example.assignment4.ui.seminar.SeminarInstructor


// POST "api/v1/seminar/" 의 response
data class CreateSeminarFetch (
    val id : Int,
    val online : Boolean,
    val participants : List<SeminarParticipant>,
    val instructors : List<SeminarInstructor>,
    val time : String,
    val name : String,
    val capacity : Int,
    val count : Int
)

// POST "/seminar/1/user/" 했을 때 위의 "participant" 항목이 List<SeminarParticipant>로 온다.
data class SeminarParticipant (
    val id : Int,
    val username : String,
    val email : String,
    val first_name : String,
    val last_name : String,
    val joined_at : String,
    val is_active : Boolean
)
