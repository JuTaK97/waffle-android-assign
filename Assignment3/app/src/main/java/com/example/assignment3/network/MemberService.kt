package com.wafflestudio.assignment3.network

import com.wafflestudio.assignment3.network.dto.FetchAllMemberResponse
import com.wafflestudio.assignment3.network.dto.FetchMemberByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MemberService {
    @GET("/waffle/members")
    suspend fun fetchAllMember(): FetchAllMemberResponse

    @GET("/waffle/members/{id}")
    suspend fun fetchDetail(@Path(value="id") id:Int) : FetchMemberByIdResponse
}
