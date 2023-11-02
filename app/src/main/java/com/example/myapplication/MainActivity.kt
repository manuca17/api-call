package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout.TabGravity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: Adapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager

        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<PostsItem>?> {
            override fun onResponse(
                call: Call<List<PostsItem>?>,
                response: Response<List<PostsItem>?>
            ) {
                val responseBody = response.body()!!
                myAdapter = Adapter(baseContext, responseBody)
                binding.recyclerView.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<PostsItem>?>, t: Throwable) {
                Log.d("MainActivity", "OnFailiure"+t.message)
            }
        })
    }
}