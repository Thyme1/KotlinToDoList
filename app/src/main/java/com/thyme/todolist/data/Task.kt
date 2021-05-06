package com.thyme.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
        @PrimaryKey(autoGenerate = true)
        val uid: Long = 0,
        var name: String,
        var date: String,
        var description: String,
        var hour: String

)

{

}