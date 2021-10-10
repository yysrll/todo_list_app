package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.databinding.ActivityTaskDetailBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding
    private lateinit var viewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 11 : Show detail task and implement delete action
        initViewModel()
        populateDetail()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DetailTaskViewModel::class.java)
    }
    private fun populateDetail() {
        val id = intent.getIntExtra(TASK_ID, -1)
        viewModel.setTaskId(id)

        viewModel.task.observe(this, {
            it?.let { item ->
                binding.apply {
                    detailEdTitle.setText(item.title)
                    detailEdDescription.setText(item.description)
                    detailEdDueDate.setText(DateConverter.convertMillisToString(item.dueDateMillis))
                }
            }

        })

        binding.btnDeleteTask.setOnClickListener {
            finish()
            viewModel.deleteTask()
        }
    }
}