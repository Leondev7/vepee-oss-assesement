package com.vp.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.vp.detail.DetailActivity;
import com.vp.favorites.FavoritesActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MovieListActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    private static final String IS_SEARCH_VIEW_ICONIFIED = "is_search_view_iconified";

    private static final String CURRENT_QUERY = "current_query";
    private String currentQuery = "Interview";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingActivityInjector;
    private SearchView searchView;
    private boolean searchViewExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        if (savedInstanceState == null) {

            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, listFragment, ListFragment.TAG)
                    .commit();

        } else {
            currentQuery = savedInstanceState.getString(CURRENT_QUERY);
            searchViewExpanded = savedInstanceState.getBoolean(IS_SEARCH_VIEW_ICONIFIED);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);

        searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        searchView.setIconified(searchViewExpanded);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.TAG);
                listFragment.submitSearchQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentQuery = newText;
                return false;
            }
        });
        searchView.setQuery(currentQuery,false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.favorites) {
            navigateToFavorites();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToFavorites(){
        Intent navigateToFavorites = new Intent(this, FavoritesActivity.class);
        startActivity(navigateToFavorites);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_SEARCH_VIEW_ICONIFIED, searchView.isIconified());
        outState.putString(CURRENT_QUERY, currentQuery);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingActivityInjector;
    }

}
