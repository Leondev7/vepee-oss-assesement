package com.vp.movies.di

import android.content.Context
import com.vp.core.storage.dao.MovieDao
import com.vp.core.storage.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): MoviesDatabase {

        return MoviesDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providesProductDao(moviesDatabase: MoviesDatabase): MovieDao {
        return moviesDatabase.moviesDao()
    }


}