package com.thyme.todolist.data.dao

import androidx.room.*
import com.thyme.todolist.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)


    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
     fun deleteAll()

    @Query("SELECT * FROM task_table ORDER BY name ASC ")
    fun getAllTasks(): Flow<List<Task>>


}