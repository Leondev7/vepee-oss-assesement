package com.vp.favorites.di

import com.vp.favorites.FavoritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentModule {

    @ContributesAndroidInjector
    abstract fun bindFavoritesFragment(): FavoritesFragment
}