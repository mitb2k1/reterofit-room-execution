package com.example.retrofitdemo

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    // Add more endpoint methods as needed...

    @GET("posts")  //http request
    fun getPosts(): Call<List<PostModel>>

//    @GET("your_api_endpoint")
//    fun getDataFromApi(): Call<List<YourEntity>>

//    @GET("data")
//    fun getAllPosts(): Call<List<YourEntity>
}
