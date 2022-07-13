package com.example.mvvmwithapicalling

import com.example.mvvmwithapicalling.model.MoviesListItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("movielist.json")
    fun getAllMovies(): Call<List<MoviesListItem>>

    companion object {
        var retrofitService: RetrofitInterface? = null

        fun getInstance() : RetrofitInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitInterface::class.java)
            }
            return retrofitService!!
        }
    }
}