package com.thyme.todolist.di

import android.content.Context
import androidx.room.Room
import com.thyme.todolist.data.AppDatabase
import com.thyme.todolist.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, AppDatabase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun provideTaskDao(
        db: AppDatabase
    ) = db.taskDao()

}