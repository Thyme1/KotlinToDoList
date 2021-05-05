package com.thyme.todolist.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.lsm.learnwordspart2.R
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task
import com.thyme.todolist.ui.base.NavigEvent
import com.thyme.todolist.ui.base.NavigateEventInfo
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Zawiera informacje aktualnie wybiernego zadania.
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(
    taskRepository: Repository
) : BaseViewModel() {
    val tasks = taskRepository.getAllTasks().asLiveData()

    companion object {
        fun provideFactory(
                assistedFactory: TaskListViewModelFactory
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create() as T
            }
        }
    }

    fun chooseTask(task: Task) {
        Log.d("Do chore", "Task name clicked: ${task.name}")
//        Toast.makeText(requireActivity(), "You have choosen task: ${task.name}", Toast.LENGTH_LONG).show()
    }

    fun goToAddTask() {
        val navigInfo = NavigateEventInfo(
            R.id.action_taskListFragment_to_addTaskFragment
        )
        _navigateToFragment.value = NavigEvent(navigInfo)
    }




}

@AssistedFactory
interface TaskListViewModelFactory {
    fun create(): TaskListViewModel

}
