package com.thyme.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.thyme.todolist.data.dao.TaskDao
import com.thyme.todolist.utils.DATABASE_NAME
import com.thyme.todolist.workers.SeedDatabaseWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//dao.insert(Task("Feed dog", "12-05-2021", "Give dog something to eat", "12:35"))
//                dao.insert(Task("Tidy my room", "17-07-2021", "Clean room", "16:20"))
//                dao.insert(Task("Mow the lawn", "11-09-2021", "Mow our lawn", "15:00"))


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                            GlobalScope.launch(Dispatchers.IO) { rePopulateDb(instance) }
                        }
                    }
                )
                .build()
        }

        suspend fun rePopulateDb(instance: AppDatabase?) {
            instance?.let { db ->
                withContext(Dispatchers.IO) {
                    val taskDao: TaskDao = db.taskDao()

                    // Hardcoded text only for tests
                    val task1 = Task(name = "Feed dog", date = "12-05-2021", description = "Give dog something to eat", hour = "12:35")
                    taskDao.insert(task1)
                    val task2 = Task(name = "Tidy my room", date = "17-07-2021", description = "Clean room", hour = "16:20")
                    taskDao.insert(task2)



                }
            }
        }
    }
}


