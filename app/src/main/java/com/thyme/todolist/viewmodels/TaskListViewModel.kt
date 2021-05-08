package com.thyme.todolist.viewmodels

import androidx.lifecycle.*
import com.thyme.todolist.data.dao.TaskDao
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Zawiera informacje aktualnie wybiernego zadania.
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskDao: TaskDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val searchQuery = state.getLiveData("searchQuery", "")


    private val tasksEventChannel = Channel<TasksEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    private val tasksFlow = (
        searchQuery.asFlow())


    val tasks = tasksFlow.asLiveData()


    fun onAddNewTaskClick() = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.NavigateToAddTaskScreen)
    }




    private fun showTaskSavedConfirmationMessage(text: String) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.ShowTaskSavedConfirmationMessage(text))
    }



    sealed class TasksEvent {
        object NavigateToAddTaskScreen : TasksEvent()

        data class ShowTaskSavedConfirmationMessage(val msg: String) : TasksEvent()
    }
}
