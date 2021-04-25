package com.thyme.todolist.data.dao

import com.thyme.todolist.data.DataSource
import com.thyme.todolist.data.Task

class TaskDao {
    fun getAllTasks(): ArrayList<Task> {
        return DataSource.tasks
    }

    fun addTask(task: Task) {
        var listOfTasks = getAllTasks()
        listOfTasks.add(task)
    }

    fun removeTask(task: Task): Boolean {
        var listOfTasks = getAllTasks()
        return listOfTasks.remove(task)
    }

    fun getLastTask(numberOfElements: Int): List<Task> {
        val items = getAllTasks()
        items.sortedByDescending { task -> task.date }
        return items.subList(0, Math.min(items.size, numberOfElements))
    }

}