package com.vp.detail.extension

import com.vp.core.storage.MovieDataEntity
import com.vp.detail.model.MovieDetail

fun MovieDetail.toEntity(movieId: String): MovieDataEntity {
    return MovieDataEntity(
        title = this.title,
        director = this.director,
        plot = this.plot,
        poster = this.poster,
        runtime = this.runtime,
        year = this.year,
        id = movieId
    )
}