package com.hako.albumlist.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hako.albumlist.R
import com.hako.albumlist.model.Album
import com.hako.base.extensions.autoNotify
import kotlinx.android.synthetic.main.item_album_card.view.*
import kotlin.properties.Delegates

class AlbumlistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items by Delegates.observable(emptyList<Album>()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new -> old.id == new.id }
        notifyDataSetChanged()
    }

    var onItemClick: (Album) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AlbumViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_album_card, parent, false),
            onItemClick
        )

    fun getItem(position: Int) = items[position]

    fun addAll(list: List<Album>) {
        items = list
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) =
        when (viewholder) {
            is AlbumViewHolder -> viewholder.bind(items[position])
            else -> throw NoWhenBranchMatchedException("Undefined viewholder")
        }
}

class AlbumViewHolder(private val view: View,
                     private val onItemClick: (Album) -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bind(album: Album) = with(view) {
        item_album_card_album_name.text = album.title
        item_album_card_container.setOnClickListener {
            onItemClick(album)
        }
    }
}