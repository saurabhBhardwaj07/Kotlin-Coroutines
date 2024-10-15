package com.saurabhbhardwaj.kotlincoroutines.ui.retrofit.single

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelperImpl
import com.saurabhbhardwaj.kotlincoroutines.data.api.RetrofitBuilder
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseBuilder
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelperImpl
import com.saurabhbhardwaj.kotlincoroutines.data.model.ApiUser
import com.saurabhbhardwaj.kotlincoroutines.databinding.ActivityRecyclerViewBinding
import com.saurabhbhardwaj.kotlincoroutines.ui.base.ApiUserAdapter
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState
import com.saurabhbhardwaj.kotlincoroutines.ui.base.ViewModelFactory


class SingleNetworkCallActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleNetworkCallViewModel


    private lateinit var adapter: ApiUserAdapter
    private  lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                arrayListOf()
            )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding. recyclerView.context,
                (  binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding. recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    binding. progressBar.visibility = View.GONE
                    renderList(it.data)
                    binding. recyclerView.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    binding. progressBar.visibility = View.VISIBLE
                    binding. recyclerView.visibility = View.GONE
                }
                is UiState.Error -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        )[SingleNetworkCallViewModel::class.java]
    }
}
