package com.hako.userlist.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hako.base.extensions.autoNotify
import com.hako.userlist.model.User
import com.hako.userlist.R
import kotlinx.android.synthetic.main.item_user_card.view.*
import kotlin.properties.Delegates

class UserlistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items by Delegates.observable(emptyList<User>()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new -> old.id == new.id }
        notifyDataSetChanged()
    }

    var onItemClick: (User) -> Unit = { }
    var onFavoriteClick: (User) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user_card, parent, false),
            onItemClick,
            onFavoriteClick
        )

    fun getItem(position: Int) = items[position]

    fun addAll(list: List<User>) {
        items = list
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) =
        when (viewholder) {
            is UserViewHolder -> viewholder.bind(items[position])
            else -> throw NoWhenBranchMatchedException("Undefined viewholder")
        }
}

class UserViewHolder(
    private val view: View,
    private val onItemClick: (User) -> Unit,
    private val onFavoriteClick: (User) -> Unit
) :
    RecyclerView.ViewHolder(view) {

    fun bind(user: User) = with(view) {
        item_user_card_real_name.text = user.realName
        item_user_card_user_name.text = user.userName
        if (user.isFavorite) {
            item_user_card_like_button.like()
        } else {
            item_user_card_like_button.dislike()
        }
        item_user_card_container.setOnClickListener {
            onItemClick(user)
        }
        item_user_card_like_button.setOnClickListener {
            item_user_card_like_button.play()
            onFavoriteClick(user)
        }
    }
}