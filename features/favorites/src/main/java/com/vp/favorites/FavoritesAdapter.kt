package com.vp.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.vp.core.di.GlideApp
import com.vp.favorites.model.FavoritesItem

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ListViewHolder>() {
    private var favouritesItems: List<FavoritesItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listItem: FavoritesItem = favouritesItems[position]
        if (NO_IMAGE != listItem.poster) {
            val density = holder.image.resources.displayMetrics.density
            GlideApp
                .with(holder.image)
                .load(listItem.poster)
                .override((300 * density).toInt(), (600 * density).toInt())
                .into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.placeholder)
        }
    }

    override fun getItemCount(): Int {
        return favouritesItems.size
    }

    fun setItems(listItems: List<FavoritesItem>) {
        favouritesItems = listItems
        notifyDataSetChanged()
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.poster)

    }

    companion object {
        private const val NO_IMAGE = "N/A"
    }
}