package com.thyme.todolist.data.dao

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thyme.todolist.MainApplication
import com.thyme.todolist.data.DataSource
import com.thyme.todolist.data.Task

class TaskDao {
    companion object {
        public const val SHARED_PREFERENCES_TAG = "taskData"
    }

    init {
        readFromSharedPreferences()
    }

    fun getAllTasks(): ArrayList<Task> {
        return DataSource.tasks
    }

    fun addTask(task: Task) {
        var listOfTasks = getAllTasks()
        listOfTasks.add(task)
        saveInSharedPreferences()
    }

    fun removeTask(task: Task): Boolean {
        var listOfTasks = getAllTasks()
        return listOfTasks.remove(task)
        saveInSharedPreferences()
    }

    fun getLastTask(numberOfElements: Int): List<Task> {
        val items = getAllTasks()
        items.sortedByDescending { task -> task.date }
        return items.subList(0, Math.min(items.size, numberOfElements))
    }

    //region SharedPreferences helper methods

    fun saveInSharedPreferences() {
        val context = MainApplication.applicationContext()
        var dataToSave: ArrayList<Task> = arrayListOf()
        dataToSave = DataSource.tasks
        saveListInSharedPreferences(context, SHARED_PREFERENCES_TAG, dataToSave)

    }

    private fun saveListInSharedPreferences(
            context: Context,
            sharedPreferencesTag: String,
            dataToSave: java.util.ArrayList<Task>) {
        var sharedPreferences = context.getSharedPreferences(sharedPreferencesTag, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(dataToSave)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(sharedPreferencesTag, json)
        editor.commit()
    }

    fun readFromSharedPreferences() {
        val context = MainApplication.applicationContext()
        val readedData: ArrayList<Task> = loadListFromSharedPreferneces(
                context, SHARED_PREFERENCES_TAG
        )


        DataSource.tasks = readedData
    }

    private fun loadListFromSharedPreferneces(
            context: Context,
            sharedPreferencesTag: String
    ): ArrayList<Task> {
        val gson = Gson()
        var sharedPreferences = context.getSharedPreferences(sharedPreferencesTag, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(SHARED_PREFERENCES_TAG, "")
        if (json == null || json.length < 10)
            return arrayListOf()
        val itemType = object : TypeToken<ArrayList<Task>>() {}.type
        val dataListFromSharedPreferneces: ArrayList<Task> =
                gson.fromJson<ArrayList<Task>>(json, itemType)
        return dataListFromSharedPreferneces
    }

    fun clearSharedPreferences(context: Context) {
        var listOfTasks = getAllTasks()
        listOfTasks.clear()
        saveInSharedPreferences()
    }

    //endregion

}