package com.thyme.todolist.data

import com.thyme.todolist.data.dao.TaskDao
import javax.inject.Inject

/**
 * Zapewnia dostep do danych
 */
class TaskRepository
@Inject
constructor(private val taskDao: TaskDao) {

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    fun getAllTasks() = taskDao.getAllTasks()
    fun deleteAllTasks() = taskDao.deleteAll()



}