package com.thyme.todolist.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thyme.todolist.data.Task
import com.thyme.todolist.data.dao.TaskDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddTaskViewModel @AssistedInject constructor(
    private val taskDao: TaskDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val task = state.get<Task>("task")

    var taskName = state.get<String>("taskName") ?: task?.name ?: ""
        set(value) {
            field = value
            state.set("taskName", value)
        }
    var taskDescription = state.get<String>("taskDescription") ?: task?.description ?: ""
        set(value) {
            field = value
            state.set("taskDescription", value)
        }
    var taskTime = state.get<String>("taskTime") ?: task?.hour ?: ""
        set(value) {
            field = value
            state.set("taskTime", value)
        }
    var taskDate = state.get<String>("taskDate") ?: task?.hour ?: ""
        set(value) {
            field = value
            state.set("taskDate", value)
        }


    private val addAddTaskEventChannel = Channel<AddEditTaskEvent>()
    val addEditTaskEvent = addAddTaskEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (taskName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        val newTask =
            Task(name = taskName, date = taskDate, description = taskDescription, hour = taskTime)
        createTask(newTask)

    }

    private fun createTask(task: Task) = viewModelScope.launch {
        taskDao.insert(task)
    }


    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addAddTaskEventChannel.send(AddEditTaskEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditTaskEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditTaskEvent()
    }
}