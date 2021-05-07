package com.thyme.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task constructor(

    var name: String,
    var date: String,
    var description: String,
    var hour: String

) {
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0

}