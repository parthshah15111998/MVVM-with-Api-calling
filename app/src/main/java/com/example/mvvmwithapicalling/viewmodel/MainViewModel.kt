package com.example.mvvmwithapicalling.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmwithapicalling.model.MoviesListItem
import com.example.mvvmwithapicalling.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val movieList = MutableLiveData<List<MoviesListItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()

        response.enqueue(object : Callback<List<MoviesListItem>?> {
            override fun onResponse(
                call: Call<List<MoviesListItem>?>,
                response: Response<List<MoviesListItem>?>
            ) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<MoviesListItem>?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}