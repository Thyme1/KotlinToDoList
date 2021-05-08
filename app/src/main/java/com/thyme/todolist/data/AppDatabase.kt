package com.thyme.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thyme.todolist.data.dao.TaskDao


@Database(
    entities = [Task::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}







