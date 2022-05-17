package com.example.githubapi.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.ui.Error
import com.example.githubapi.ui.Loading
import com.example.githubapi.ui.Success
import com.example.githubapi.ui.adapter.UsersAdapter
import com.example.githubapi.ui.viewModel.MainViewModel
import com.example.githubapi.ui.vo.UserVO

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private val btnFetch: Button by lazy {
        findViewById(R.id.btn_fetch)
    }
    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progress_bar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        setupView()
    }

    private fun setupObservers() {
        viewModel.users.observe(this) { result ->
            when (result) {
                Loading -> {
                    progressBar.isVisible = true
                    btnFetch.isGone = true
                }
                is Error -> {
                    Toast.makeText(this, result.genericMsg, Toast.LENGTH_SHORT).show()
                }
                is Success -> {
                    prepareRecyclerView(result.data)
                    progressBar.isGone = true
                }
            }
        }
    }

    private fun prepareRecyclerView(data: List<UserVO>) {
        findViewById<RecyclerView>(R.id.rv_users).adapter = UsersAdapter(data)
    }

    private fun setupView() {
        btnFetch.setOnClickListener {
            viewModel.fetchUsers()
        }
    }
}