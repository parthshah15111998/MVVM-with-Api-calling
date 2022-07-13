package com.example.mvvmwithapicalling.repository

import com.example.mvvmwithapicalling.RetrofitInterface

class MainRepository constructor(private val retrofitInterface: RetrofitInterface) {
    fun getAllMovies() = retrofitInterface.getAllMovies()
}