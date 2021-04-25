package com.thyme.todolist.ui.toDo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thyme.todolist.data.Task

class TaskViewModel {
    var task: LiveData<Task> = MutableLiveData<Task>()


}