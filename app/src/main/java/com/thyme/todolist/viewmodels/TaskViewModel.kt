package com.thyme.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thyme.todolist.data.Task

class TaskViewModel {
    var task: LiveData<Task> = MutableLiveData<Task>()


}