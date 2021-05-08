package com.thyme.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thyme.todolist.data.dao.TaskDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao


    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @Singleton private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().taskDao()


            val task1 = Task(
                name = "Feed dog",
                date = "12-05-2021",
                description = "Give dog something to eat",
                time = "12:35"
            )
            dao.insert(task1)
            val task2 = Task(
                name = "Tidy my room",
                date = "17-07-2021",
                description = "Clean room",
                time = "16:20"
            )
            dao.insert(task2)


        }
    }
}




