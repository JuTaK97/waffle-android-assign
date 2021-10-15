package com.wafflestudio.assignment3.network.dto

import com.wafflestudio.assignment3.model.Member

// Use if you need it
data class FetchAllMemberResponse(
    val statusCode: Int,
    val body: List<Member>
)