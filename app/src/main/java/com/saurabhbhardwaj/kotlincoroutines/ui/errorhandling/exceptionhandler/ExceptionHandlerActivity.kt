package com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.exceptionhandler

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
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
import com.saurabhbhardwaj.kotlincoroutines.databinding.ItemLayoutBinding
import com.saurabhbhardwaj.kotlincoroutines.ui.base.ApiUserAdapter
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState
import com.saurabhbhardwaj.kotlincoroutines.ui.base.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExceptionHandlerActivity : AppCompatActivity() {

//    private lateinit var viewModel: ExceptionHandlerViewModel
    private val viewModel: ExceptionHandlerViewModel by viewModels()
    private lateinit var adapter: ApiUserAdapter

    private  lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
//        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ApiUserAdapter(
            arrayListOf()
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
               binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
       binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    binding. progressBar.visibility = View.GONE
                    renderList(it.data)
                    binding.recyclerView.visibility = View.VISIBLE
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

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

//    private fun setupViewModel() {
//        viewModel = ViewModelProvider(
//            this, ViewModelFactory(
//                ApiHelperImpl(RetrofitBuilder.apiService),
//                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
//            )
//        )[ExceptionHandlerViewModel::class.java]
//    }
}
