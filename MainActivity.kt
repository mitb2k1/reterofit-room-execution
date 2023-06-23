package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
//import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var fetch: Button
    private lateinit var repository : YourRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = YourDatabase.getInstance(applicationContext)
        repository = YourRepository(db.yourDao())
        fetch=findViewById(R.id.button3)

        callRetrofit()

        fetch.setOnClickListener{
            fetchdata()
        }
    }

    private fun fetchdata() {
        CoroutineScope(Dispatchers.IO).launch {
            val posts = repository.getAllPosts()
            withContext(Dispatchers.Main) {
                displayPosts(posts)
            }
        }
    }

    private fun displayPosts(posts: List<PostModel>) {
        val stringBuilder = StringBuilder()
        for (post in posts) {
            stringBuilder.append("UserID: ${post.userId}\nID: ${post.id}\nTitle: ${post.title}\nBody: ${post.body}\n\n")
        }
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Posts")
            .setMessage(stringBuilder.toString())
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }

    private fun callRetrofit() {
        try {
            val serviceGenerator = ServiceGenerator.buildService(ApiInterface::class.java)
            val call = serviceGenerator.getPosts()
            call.enqueue(object : Callback<List<PostModel>> {
                override fun onResponse(
                    call: Call<List<PostModel>?>,
                    response: Response<List<PostModel>?>
                ) {

                    Log.d("response","response -> ${response.body()}")
                    CoroutineScope(Dispatchers.IO).launch {
                        //val posts = response.body()
                        repository.insert(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<PostModel>?>, t: Throwable) {
                    Log.d("response","response -> fail")
                }

            })
        }catch (e : Exception){
            e.message
        }
    }
}







