package com.example.retrofitdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [PostModel::class], version = 1)

@Database(entities = [PostModel::class], version = 3)
abstract class YourDatabase : RoomDatabase(){
    abstract fun yourDao(): YourDao

    companion object {
        @Volatile
        private var INSTANCE: YourDatabase? = null

        fun getInstance(context: Context): YourDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YourDatabase::class.java,
                    "my-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    override fun close() {
        INSTANCE=null
        super.close()
    }
}