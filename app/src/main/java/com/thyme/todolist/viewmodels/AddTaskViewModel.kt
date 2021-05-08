package com.thyme.todolist.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thyme.todolist.data.Task
import com.thyme.todolist.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTaskViewModel
@Inject
constructor(private val taskRepository: TaskRepository) : ViewModel() {

    fun insertTask(task: Task) = viewModelScope.launch {
        taskRepository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    val allToDos = taskRepository.getAllTasks().asLiveData()

}