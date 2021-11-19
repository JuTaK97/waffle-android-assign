package com.wafflestudio.assignment3.db



import androidx.lifecycle.LiveData
import androidx.room.*
import com.wafflestudio.assignment3.model.Member


@Dao
interface MemberDao {

    @Query("SELECT * FROM member_table")
    fun getAllMember() : LiveData<List<Member>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMembers(members: List<Member>)
}
