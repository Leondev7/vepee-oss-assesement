package com.vp.movies.di

import com.vp.detail.di.DetailActivityModule
import com.vp.favorites.di.FavoriteActivityModule
import com.vp.list.di.MovieListActivityModule
import com.vp.movies.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        MovieListActivityModule::class,
        FavoriteActivityModule::class,
        DetailActivityModule::class,
        DatabaseModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoviesApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MoviesApplication)
}