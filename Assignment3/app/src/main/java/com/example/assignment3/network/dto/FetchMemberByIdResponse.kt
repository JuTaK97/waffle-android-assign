package com.wafflestudio.assignment3.network.dto

import com.wafflestudio.assignment3.model.Member

// Use if you need it
data class FetchMemberByIdResponse (
    val statusCode: Int,
    val body: Member
)