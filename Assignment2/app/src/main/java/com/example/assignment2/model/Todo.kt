package com.example.assignment2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo (
    @ColumnInfo(name="title")
    val title : String,
    @ColumnInfo(name="content")
    val content : String
        ){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}