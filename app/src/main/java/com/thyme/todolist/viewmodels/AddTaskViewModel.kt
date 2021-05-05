package com.thyme.todolist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AddTaskViewModel @AssistedInject constructor(
        repository: Repository,
        @Assisted private val taskUid: Long
) : BaseViewModel() {
    var tasks = repository.getAllTasks().asLiveData()

    companion object {
        fun provideFactory(
                assistedFactory: AddTaskViewModelFactory,
                subjectUid: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(subjectUid) as T
            }
        }
    }

    fun addTask(task: Task) {

    }

}

@AssistedFactory
interface AddTaskViewModelFactory {
    fun create(subjectUid: Long) : AddTaskViewModel

}
