package com.thyme.todolist.data.dao

import com.thyme.todolist.data.DataSource
import com.thyme.todolist.data.Task

class TaskDao {
    fun getAllTasks(): ArrayList<Task> {
        return DataSource.tasks
    }
}