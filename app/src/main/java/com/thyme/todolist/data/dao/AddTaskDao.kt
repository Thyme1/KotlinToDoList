package com.thyme.todolist.data.dao

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thyme.todolist.MainApplication
import com.thyme.todolist.data.DataSource
import com.thyme.todolist.data.Task

class AddedTaskDao {

    companion object {
        public const val SHARED_PREFERENCES_TAG = "AddTaskData"
    }

    init {
        readFromSharedPreferences()
    }


    /** Returns all learning process data for the given subject,level,chapter Ids */
    fun getAddedTask(): ArrayList<Task> {
        return DataSource.tasks
    }


    /** Add learning process Data */
    fun addTask(task: Task) {
        with(task) {
            var listOfTasks = getAddedTask()
            listOfTasks.add(task)
            saveInSharedPreferences()
        }
    }

    /** Remove learning process data from list */
    fun removeAddedTask(task: Task): Boolean {
        with(task) {
            var listOfTasks = getAddedTask()
            return listOfTasks.remove(task)
        }
    }


    // region SharedPreferences helper methods

    /** save DataSource.learningProcessData into SharedPreferences */
    fun saveInSharedPreferences() {
        val context = MainApplication.applicationContext()
        var dataToSave: ArrayList<Task> = arrayListOf()
        dataToSave.addAll(DataSource.tasks)
        saveListInSharedPreferences(context, SHARED_PREFERENCES_TAG, dataToSave)
    }


    /** save givent list of learningProcessData into given tag of the shared preferences*/
    private fun saveListInSharedPreferences(
        context: Context,
        sharedPreferencesTag: String,
        dataToSave: java.util.ArrayList<Task>
    ) {
        var sharedPreferences =
            context.getSharedPreferences(sharedPreferencesTag, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(dataToSave)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(sharedPreferencesTag, json)
        editor.commit()
    }


    /** Read DataSource.learningProcessData from the sharedPreferences*/
    fun readFromSharedPreferences() {
        val context = MainApplication.applicationContext()
        val readedData: List<Task> = loadListFromSharedPreferneces(
            context, SHARED_PREFERENCES_TAG
        )
        if (DataSource.tasks == null)
            DataSource.tasks.clear()

    }

    private fun loadListFromSharedPreferneces(
        context: Context,
        sharedPreferencesTag: String
    ): List<Task> {
        val gson = Gson()
        var sharedPreferences =
            context.getSharedPreferences(sharedPreferencesTag, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(SHARED_PREFERENCES_TAG, "")
        if (json == null || json.length < 10)
            return arrayListOf()
        val itemType = object : TypeToken<List<Task>>() {}.type
        val dataListFromSharedPreferneces: List<Task> =
            gson.fromJson<List<Task>>(json, itemType)
        return dataListFromSharedPreferneces
    }


    //endregion

}


