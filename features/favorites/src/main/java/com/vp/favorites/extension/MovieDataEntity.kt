package com.vp.favorites.extension

import com.vp.core.storage.MovieDataEntity
import com.vp.favorites.model.FavoritesItem

fun MovieDataEntity.toFavoriteItem(): FavoritesItem{
    return  FavoritesItem(
        title = this.title,
        year = this.year,
        imdbID = this.id,
        poster = this.poster
    )
}