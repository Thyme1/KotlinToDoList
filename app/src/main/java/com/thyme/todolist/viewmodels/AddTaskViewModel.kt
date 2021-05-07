package com.thyme.todolist.viewmodels

import androidx.lifecycle.asLiveData
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Zawiera informacje aktualnie wybiernego przedmiotu do nauki.
 */

@HiltViewModel
class AddTaskViewModel @Inject public constructor(
    subjectRepository: Repository
) : BaseViewModel() {
    val tasks = subjectRepository.getAllTasks().asLiveData()

    fun chooseTask(task: Task) {}

}