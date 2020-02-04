package com.hako.albumlist.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hako.albumlist.R
import com.hako.albumlist.model.AlbumViewable
import com.hako.base.extensions.autoNotify
import kotlinx.android.synthetic.main.item_album_card.view.*
import kotlin.properties.Delegates

class AlbumlistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items by Delegates.observable(emptyList<AlbumViewable>()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new -> old.id == new.id }
        notifyDataSetChanged()
    }

    var onItemClick: (AlbumViewable) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_album_card, parent, false),
            onItemClick
        )

    fun getItem(position: Int) = items[position]

    fun addAll(list: List<AlbumViewable>) {
        items = list
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) =
        when (viewholder) {
            is UserViewHolder -> viewholder.bind(items[position])
            else -> throw NoWhenBranchMatchedException("Undefined viewholder")
        }
}

class UserViewHolder(private val view: View,
                     private val onItemClick: (AlbumViewable) -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bind(album: AlbumViewable) = with(view) {
        item_album_card_album_name.text = album.title
        item_album_card_container.setOnClickListener {
            onItemClick(album)
        }
    }
}