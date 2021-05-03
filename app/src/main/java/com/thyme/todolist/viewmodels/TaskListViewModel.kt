package com.thyme.todolist.viewmodels

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.thyme.todolist.MainApplication
import com.thyme.todolist.R
import com.thyme.todolist.data.Database
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task
import com.thyme.todolist.ui.base.NavigEvent
import com.thyme.todolist.ui.base.NavigateEventInfo
import com.thyme.todolist.ui.toDo.list.TaskListFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.security.auth.Subject

/**
 * Zawiera informacje aktualnie wybiernego zadania.
 */
@HiltViewModel
class TaskListViewModel @Inject public constructor(
    subjectRepository: Repository
) : BaseViewModel() {
    val tasks = subjectRepository.getAllTasks().asLiveData()

    fun chooseTask(task: Task) {
        Log.d("Do chore", "Task name clicked: ${task.name}")
        var bundle = Bundle()
        bundle.putString(TaskListFragment.TAG,task.name)
        Toast.makeText(requireActivity(), "You have choosen task: ${task.name}", Toast.LENGTH_LONG).show()
    }

    fun goToAddTask() {
        val navigInfo = NavigateEventInfo(
            R.id.action_taskListFragment_to_addTaskFragment
        )
        _navigateToFragment.value = NavigEvent(navigInfo)
    }
    }

    fun clearPreferences() {
        val context = MainApplication.applicationContext()
        Database.taskDao.clearSharedPreferences(context)
        Toast.makeText(requireActivity(), "SharedPreferences cleared", Toast.LENGTH_LONG).show()
        mAdapter.notifyDataSetChanged()

    }

}
