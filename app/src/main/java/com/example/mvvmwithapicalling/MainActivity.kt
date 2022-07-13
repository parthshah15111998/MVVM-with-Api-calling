package com.example.mvvmwithapicalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithapicalling.adapter.MainAdapter
import com.example.mvvmwithapicalling.databinding.ActivityMainBinding
import com.example.mvvmwithapicalling.repository.MainRepository
import com.example.mvvmwithapicalling.viewmodel.MainViewModel
import com.example.mvvmwithapicalling.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitInterface.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(this, Observer {
            Log.d("MainActivity", "onCreate: $it")
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllMovies()

    }
}