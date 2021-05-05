package com.thyme.todolist.data

import android.content.Context
import android.util.Log
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


@Database(
        entities = [
            Task::class,
        ], version = 2, exportSchema = false
)
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


                    val task1 = Task(name = "Do homework", description = "Make a list of tasks for LSM", date = "12-05-2021", hour = "15:00")
                    val task2 = Task(name = "Tidy my room", description = "Put things on their place, vacuum", date = "15-06-2021", hour = "17:00")
                    val task3 = Task(name = "Feed dog", description = "Give my dog something to eat", date = "14-05-2021", hour = "12:00")
                    val taskid1 = taskDao.insert(task1)
                    val taskid2 = taskDao.insert(task2)
                    val taskid3 = taskDao.insert(task3)




                    if (task1.uid == task2.uid)
                        Log.d("Error", "Rers")

                    if (task1 == task3)
                        Log.d("Error2", "Rers 2")

                }
            }
        }
    }
}