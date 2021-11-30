package com.su.amovie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.su.amovie.R
import com.su.amovie.data.MainRepository
import com.su.amovie.data.network.RetrofitBuilder
import com.su.amovie.ui.base.ViewModelFactory
import com.su.amovie.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MainRepository(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        // Retrieves data from data
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val gridLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = gridLayoutManager
        adapter = MovieAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }


    private fun setupObservers() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val txtErrorMessage = findViewById<TextView>(R.id.txt_error_msg)
        viewModel.movies.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        txtErrorMessage.visibility = View.GONE
                        resource.data?.let { movies ->
                            retrieveList(movies)
                            Log.w("main","result: ${movies}")
                        }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        txtErrorMessage.visibility = View.VISIBLE
                        txtErrorMessage.text = it.message
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        txtErrorMessage.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun retrieveList(users: List<MovieUiModel>) {
        adapter.apply {
            addMovies(users)
            notifyDataSetChanged()
        }
    }
}