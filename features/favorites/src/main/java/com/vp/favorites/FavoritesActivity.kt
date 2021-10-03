package com.vp.favorites

import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import com.vp.favorites.R
import com.vp.favorites.FavoritesFragment
import dagger.android.AndroidInjector

class FavoritesActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        if (savedInstanceState == null) {
            val favoritesFragment = FavoritesFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, favoritesFragment, FavoritesFragment.TAG)
                .commit()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingActivityInjector
    }
}