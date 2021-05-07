package com.thyme.todolist.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.thyme.todolist.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TaskDao : BaseDao<Task> {

    @Query("SELECT * FROM task_table")
    abstract fun getAllTasks(): Flow<List<Task>>


}