package com.thyme.todolist.data

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


object DataSource {
    private var _tasks: ArrayList<Task>? = null
    val tasks: ArrayList<Task>
        get() {
            if (_tasks == null) {
                MockTasks()
            }
            return _tasks!!
        }

    val taskArray =
            arrayOf(arrayOf("Do homework", "Make a list of tasks for LSM"),
                    arrayOf("Tidy my room", "Put things on their place, vacuum"),
                    arrayOf("Buy new shoes", "Buy running shoes"),
                    arrayOf("Wash dishes", "Turn on the dishwasher"),
                    arrayOf("Do laundry", "Do laundry because I don't have any clean clothes"),
                    arrayOf("Feed dog", "Give my dog something to eat "))

    private fun MockTasks() {
        _tasks = ArrayList()

        repeat((6..10).random())
        {
            val index = kotlin.random.Random.nextInt(taskArray.size)

            val newTask = Task(
                    taskArray[(taskArray.indices).random()][0],
                    getRandDate(),
                    taskArray[(taskArray.indices).random()][1],
                    taskArray[(taskArray.indices).random()][0] + "\t\t" + getRandDate()

            )
            _tasks!!.add(newTask)
        }
    }


    private fun getRandDate(): String {
        val year = 2021
        val month: Int = randBetween(0, 11)
        val gc = GregorianCalendar(year, month, 12)
        val day: Int = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_MONTH))
        val dfDateTime = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        gc.set(year, month, day)
        return dfDateTime.format(gc.time)
    }

    private fun randBetween(start: Int, end: Int): Int {
        return start + (Math.random() * (end - start)).roundToInt()
    }


}