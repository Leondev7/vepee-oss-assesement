package com.vp.core.storage

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDataEntity(
    @PrimaryKey val id: String,
    @NonNull @ColumnInfo(name = "Title") val title: String,
    @NonNull @ColumnInfo(name = "Year") val year: String,
    @NonNull @ColumnInfo(name = "Runtime") val runtime: String,
    @NonNull @ColumnInfo(name = "Director") val director: String,
    @NonNull @ColumnInfo(name = "Plot") val plot: String,
    @NonNull @ColumnInfo(name = "Poster") val poster: String
)