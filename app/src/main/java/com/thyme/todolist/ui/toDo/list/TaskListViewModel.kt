package com.thyme.todolist.ui.toDo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task

/**
 * Zawiera informacje aktualnie wybiernego zadania.
 */
class TaskListViewModel : ViewModel() {
    var subjects: LiveData<ArrayList<Task>> = Repository.getAllTasks()


}
