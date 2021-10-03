package com.vp.core.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vp.core.storage.MovieDataEntity
import com.vp.core.storage.dao.MovieDao

@Database(entities = [MovieDataEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MoviesDatabase::class.java,
                    "movies_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}