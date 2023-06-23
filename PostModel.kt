package com.example.retrofitdemo

import androidx.room.Entity
import androidx.room.PrimaryKey

//import okhttp3.ResponseBody
@Entity(tableName = "PostModel")
data class PostModel (
    @PrimaryKey val userId : Int?=null,
    val id: String?=null,
    val title: String?=null,
    val body: String?=null,
        )