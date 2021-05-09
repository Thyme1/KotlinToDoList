package com.thyme.todolist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thyme.todolist.data.Task
import com.thyme.todolist.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel
@Inject
constructor(private val taskRepository: TaskRepository) : ViewModel() {


    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            taskRepository.deleteAllTasks()
        }
    }


}
