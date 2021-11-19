package com.example.assignment4.ui.user


// User 정보 수정할 때 사용
data class UserInfoEditRequest (
    val username : String,
    val email : String,
    val first_name : String,
    val last_name : String
)
