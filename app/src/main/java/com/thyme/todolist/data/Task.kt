package com.thyme.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
@Parcelize
data class Task (
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0L,

    var name: String,
    var date: String,
    var description: String,
    var time: String

) {


}