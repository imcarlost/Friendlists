package com.hako.userlist.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hako.base.extensions.autoNotify
import com.hako.userlist.model.UserViewable
import com.hako.friendlist_userlist.R
import kotlinx.android.synthetic.main.item_user_card.view.*
import kotlin.properties.Delegates

class UserlistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items by Delegates.observable(emptyList<UserViewable>()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new -> old.id == new.id }
        notifyDataSetChanged()
    }

    var onItemClick: (UserViewable) -> Unit = { }
    var onFavoriteClick: (UserViewable) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user_card, parent, false),
            onItemClick,
            onFavoriteClick
        )

    fun getItem(position: Int) = items[position]

    fun addAll(list: List<UserViewable>) {
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
                     private val onItemClick: (UserViewable) -> Unit,
                     private val onFavoriteClick: (UserViewable) -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bind(user: UserViewable) = with(view) {
        item_user_card_real_name.text = user.realName
        item_user_card_user_name.text = user.userName
        item_user_card_container.setOnClickListener {
            onItemClick(user)
        }
        item_user_card_like_button.setOnClickListener {
            onFavoriteClick(user)
        }
    }

    private fun setFavorite(status: Boolean) {
        when (status) {
            true -> view.item_user_card_like_button.like()
            false -> view.item_user_card_like_button.dislike()
        }
    }
}