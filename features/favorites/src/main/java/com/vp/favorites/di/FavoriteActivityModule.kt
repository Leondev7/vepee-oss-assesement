package com.vp.favorites.di

import com.vp.favorites.FavoritesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteActivityModule {

    @ContributesAndroidInjector(modules = [FavoriteFragmentModule::class, FavoritesViewModelsModule::class])
    abstract fun bindFavoritesActivity(): FavoritesActivity
}