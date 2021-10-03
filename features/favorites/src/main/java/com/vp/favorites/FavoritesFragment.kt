package com.vp.favorites

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.ViewAnimator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vp.favorites.model.FavoritesItem
import com.vp.favorites.viewmodel.FavoritesState
import com.vp.favorites.viewmodel.FavoritesViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var favoritesAdapter: FavoritesAdapter
    lateinit var viewAnimator: ViewAnimator
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        favoritesViewModel = ViewModelProviders.of(this, factory).get(
            FavoritesViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        viewAnimator = view.findViewById(R.id.viewAnimator)
        progressBar = view.findViewById(R.id.progressBar)
        initList()
        favoritesViewModel.favourites()
            .observe(viewLifecycleOwner, { items: List<FavoritesItem> ->
                setItemsData(
                    favoritesAdapter, items
                )
            })
        favoritesViewModel.state()
            .observe(viewLifecycleOwner, { state: FavoritesState -> handleState(state) })
    }

    private fun initList() {
        favoritesAdapter = FavoritesAdapter()
        recyclerView.adapter = favoritesAdapter
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(
            context,
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
        )
        recyclerView.layoutManager = layoutManager
    }

    private fun showProgressBar() {
        viewAnimator.displayedChild = viewAnimator.indexOfChild(progressBar)
    }

    private fun showList() {
        viewAnimator.displayedChild = viewAnimator.indexOfChild(recyclerView)
    }

    private fun handleState(uiState: FavoritesState) {
        when (uiState) {
            FavoritesState.LOADED -> {
                showList()
            }
            FavoritesState.IN_PROGRESS -> {
                showProgressBar()
            }
        }
    }

    private fun setItemsData(listAdapter: FavoritesAdapter, favorites: List<FavoritesItem>) {
        listAdapter.setItems(favorites)
    }

    companion object {
        const val TAG = "ListFragment"
    }
}