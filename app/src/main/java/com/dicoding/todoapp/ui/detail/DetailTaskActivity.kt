package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import java.lang.NullPointerException

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var taskViewModel: DetailTaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        val taskId = intent.getIntExtra(TASK_ID, 0)
        taskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        var title = findViewById<EditText>(R.id.detail_ed_title)
        var desc = findViewById<EditText>(R.id.detail_ed_description)
        var dueDate = findViewById<EditText>(R.id.detail_ed_due_date)
        var delete = findViewById<Button>(R.id.btn_delete_task)

        taskViewModel.setTaskId(taskId)

        taskViewModel.task.observe(this) {
            try {
                title.setText(it.title)
                desc.setText(it.description)
                dueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            } catch (e: NullPointerException) {
                finish()
            }
        }

        delete.setOnClickListener {
            taskViewModel.deleteTask()
        }
    }
}