package com.vp.core.storage.dao

import androidx.room.*
import com.vp.core.storage.MovieDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM moviedataentity")
    fun getAll(): Flow<List<MovieDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieDataEntity)

    @Delete
    fun deleteMovie(movie: MovieDataEntity)

    @Query("SELECT * FROM moviedataentity WHERE id = :moveId LIMIT 1")
    fun getMovieById(moveId: String): MovieDataEntity?

}
