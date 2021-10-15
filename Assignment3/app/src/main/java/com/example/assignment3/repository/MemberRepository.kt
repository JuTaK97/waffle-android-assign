package com.wafflestudio.assignment3.repository

import com.wafflestudio.assignment3.db.MemberDao
import com.wafflestudio.assignment3.model.Member
import com.wafflestudio.assignment3.network.MemberService

// TODO
class MemberRepository constructor(
    private val memberDao: MemberDao,
    private val memberService: MemberService
) {

    suspend fun getAllMember() : List<Member> = memberDao.getAllMember()
    suspend fun fetchAllMember() {
        memberDao.saveMembers(memberService.fetchAllMember().body)
    }
    suspend fun fetchDetail(id:Int) : Member {
        return memberService.fetchDetail(id).body
    }

    companion object {
        @Volatile
        private var INSTANCE: MemberRepository? = null

        @JvmStatic
        fun getInstance(memberDao: MemberDao, memberService: MemberService) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MemberRepository(memberDao, memberService).also { INSTANCE = it }
            }
    }
}
