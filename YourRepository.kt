package com.example.retrofitdemo

import android.util.Log
import androidx.lifecycle.LiveData

class YourRepository(private val yourDao: YourDao) {


    suspend fun insert(postModel: List<PostModel>) {
        yourDao.insertData(postModel)
    }


    suspend fun getAllPosts(): List<PostModel> {
        return yourDao.getAllPosts()
    }
}