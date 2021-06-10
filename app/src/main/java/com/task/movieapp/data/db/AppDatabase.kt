package com.task.movieapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.movieapp.data.db.converters.StringListConverter
import com.task.movieapp.data.db.entities.MovieDb

@Database(entities = [MovieDb::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "movie-app-database")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
