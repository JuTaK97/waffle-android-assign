package com.example.assignment4.dto

data class ErrorMessage (
    val email: List<String>?,
    val password: List<String>?,
    val non_field_errors : List<String>?,
    val detail : List<String>?,
    val role: List<String>?,
    val username: List<String>?,
    val first_name: List<String>?,
    val last_name: List<String>?,
    val time: List<String>?,
    val name: List<String>?,
    val capacity : List<String>?,
    val count : List<String>?
)
fun parsing(errorMessage: ErrorMessage?) : String {
    val builder = StringBuilder()
    var flag = false
    if(errorMessage?.email!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Email: ")
        builder.append(errorMessage.email[0])

    }
    if(errorMessage?.username!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Username: ")
        builder.append(errorMessage.username[0])
    }
    if(errorMessage?.password!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Password: ")
        builder.append(errorMessage.password[0])

    }
    if(errorMessage?.non_field_errors!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append(errorMessage.non_field_errors[0])
    }
    if(errorMessage?.detail!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append(errorMessage.detail[0])
    }
    if(errorMessage?.role!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Role: ")
        builder.append(errorMessage.role[0])
    }
    if(errorMessage?.first_name!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("First name: ")
        builder.append(errorMessage.first_name[0])
    }
    if(errorMessage?.last_name!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Last name: ")
        builder.append(errorMessage.last_name[0])
    }
    if(errorMessage?.time!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Time: ")
        builder.append(errorMessage.time[0])
    }
    if(errorMessage?.name!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Name: ")
        builder.append(errorMessage.name[0])
    }
    if(errorMessage?.capacity!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Capacity: ")
        builder.append(errorMessage.capacity[0])
    }
    if(errorMessage?.count!=null) {
        if(flag) builder.append("\n")
        flag = true
        builder.append("Count: ")
        builder.append(errorMessage.count[0])
    }

    return builder.toString()
}