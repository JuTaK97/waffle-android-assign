package com.example.assignment4.ui.seminar.create.dto


// POST "api/v1/seminar/" 할 때 보내야 하는 dto
data class CreateSeminarRequest (
    val online : Boolean?,
    val time : String,
    val name : String,
    val capacity : Int,
    val count : Int
)
