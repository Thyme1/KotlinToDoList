package com.thyme.todolist.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thyme.todolist.ADD_TASK_RESULT_OK
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Zawiera informacje aktualnie wybiernego przedmiotu do nauki.
 */

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepository: Repository,
    @Assisted private val state: SavedStateHandle

) : ViewModel() {
    val tasks = taskRepository.getAllTasks().asLiveData()


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
    var taskDate = state.get<String>("taskDate") ?: task?.date ?: ""
        set(value) {
            field = value
            state.set("taskDate", value)
        }
    var taskTime = state.get<String>("taskTime") ?: task?.time ?: ""
        set(value) {
            field = value
            state.set("taskTime", value)
        }

    private val addTaskEventChannel = Channel<AddTaskEvent>()
    val addTaskEvent = addTaskEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (taskName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }


        val newTask =
            Task(name = taskName, description = taskDescription, date = taskDate, time = taskTime)
        createTask(newTask)
    }


    private fun createTask(task: Task) = viewModelScope.launch {
        taskRepository.taskDao.insert(task)
        addTaskEventChannel.send(AddTaskEvent.NavigateBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addTaskEventChannel.send(AddTaskEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddTaskEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddTaskEvent()
        data class NavigateBackWithResult(val result: Int) : AddTaskEvent()
    }

}
@AssistedFactory
interface AddTaskViewModelFactory {
    fun create(chapterUid: Long): AddTaskViewModel

}