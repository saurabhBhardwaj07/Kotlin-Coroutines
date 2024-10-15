package com.saurabhbhardwaj.kotlincoroutines.ui.task.twotasks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelperImpl
import com.saurabhbhardwaj.kotlincoroutines.data.api.RetrofitBuilder
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseBuilder
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelperImpl
import com.saurabhbhardwaj.kotlincoroutines.databinding.ActivityLongRunningTaskBinding
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState
import com.saurabhbhardwaj.kotlincoroutines.ui.base.ViewModelFactory

class TwoLongRunningTasksActivity : AppCompatActivity() {

    private lateinit var viewModel: TwoLongRunningTasksViewModel
    private lateinit var binding: ActivityLongRunningTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLongRunningTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textView.text = it.data
                    binding.textView.visibility = View.VISIBLE
                }

                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textView.visibility = View.GONE
                }

                is UiState.Error -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.startLongRunningTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        )[TwoLongRunningTasksViewModel::class.java]
    }
}
