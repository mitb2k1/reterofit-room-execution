package com.example.retrofitdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface YourDao {

    //please look into how room dao is implemented if i have to do all things you wont understand your mistake from here you take this as it wont take much time for me

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(postModel: List<PostModel>)

    @Query("SELECT * FROM PostModel")
    fun getAllPosts(): List<PostModel>
}
