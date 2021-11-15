package com.example.assignment4.ui.seminar.detail


// GET "api/v1/seminar/1/" 했을 때의 response
// DetailSeminarActivity 에서 사용

// POST api/v1/seminar/{seminar_id}/user/ 의 response 도 동일

data class DetailSeminarFetch (

    val id : Int,
    val online : Boolean,
    val participants: List<DetailParticipant>,
    val instructors : List<DetailInstructor>,
    val time : String,
    val name : String,
    val capacity : Int,
    val count : Int
)

data class DetailParticipant (

    val id: Int,
    val username : String,
    val email : String,
    val first_name : String,
    val last_name : String,
    val joined_at : String,
    val is_active : Boolean,
    val dropped_at : String?
)

data class DetailInstructor (

    val id : Int,
    val username : String,
    val email: String,
    val first_name : String,
    val last_name: String,
    val joined_at: String
)
