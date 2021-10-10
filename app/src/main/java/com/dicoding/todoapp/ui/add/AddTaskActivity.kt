package com.dicoding.todoapp.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.databinding.ActivityAddTaskBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: AddTaskViewModel
    private var dueDateMillis: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.add_task)
        initViewModel()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(AddTaskViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                //TODO 12 : Create AddTaskViewModel and insert new task to database
                insertTask()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun insertTask() {
        val title = binding.addEdTitle.text.toString().trim()
        val desc = binding.addEdDescription.text.toString().trim()
        val dueDate = binding.addTvDueDate.text.toString().trim()

        if (title.isNotBlank() && desc.isNotBlank() && dueDate.isNotBlank() && !dueDate.equals
                (getString(R.string.due_date), false)) {
            viewModel.insertTask(Task(title = title, description = desc, dueDateMillis = dueDateMillis))
            finish()
        } else {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.add_tv_due_date).text = dateFormat.format(calendar.time)

        dueDateMillis = calendar.timeInMillis
    }
}