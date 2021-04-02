package com.thyme.todolist.data

import com.thyme.todolist.data.dao.TaskDao

object Database {

    var _taskDao: TaskDao? = null
    val taskDao: TaskDao
        get() {
            if (_taskDao == null)
                _taskDao = TaskDao()
            return _taskDao!!
        }

}