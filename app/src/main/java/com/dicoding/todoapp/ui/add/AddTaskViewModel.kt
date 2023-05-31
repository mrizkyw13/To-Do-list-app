package com.dicoding.todoapp.ui.add

import androidx.lifecycle.ViewModel
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository

class AddTaskViewModel(private val taskRepository : TaskRepository) : ViewModel() {

    suspend fun addTask(task: Task){
        taskRepository.insertTask(task)
    }
}