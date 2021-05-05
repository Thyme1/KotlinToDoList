package com.thyme.todolist.data

import com.thyme.todolist.data.dao.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Zapewnia dostep do danych
 */
@Singleton
class Repository @Inject constructor(
        val taskDao: TaskDao

) {
    fun getAllTasks() = taskDao.getAllTasks()


}