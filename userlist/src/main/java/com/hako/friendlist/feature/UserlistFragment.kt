package com.hako.friendlist.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hako.base.extensions.toast
import com.hako.friendlist.model.UserViewable
import com.hako.friendlist.widget.UserlistAdapter
import com.hako.friendlist_userlist.R
import kotlinx.android.synthetic.main.fragment_userlist.*

class UserlistFragment : Fragment() {

    private val chatAdapter by lazy { UserlistAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_userlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    private fun setRecycler() {
        fragment_userlist_recycler_container.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter.apply {
                addAll(
                    listOf(
                        UserViewable(1, "Carlos Martinez", "carlitos"),
                        UserViewable(2, "Carlos Martinez", "carlitos"),
                        UserViewable(3, "Carlos Martinez", "carlitos"),
                        UserViewable(4, "Carlos Martinez", "carlitos"),
                        UserViewable(5, "Carlos Martinez", "carlitos"),
                        UserViewable(6, "Carlos Martinez", "carlitos"),
                        UserViewable(7, "Carlos Martinez", "carlitos"),
                        UserViewable(8, "Carlos Martinez", "carlitos"),
                        UserViewable(9, "Carlos Martinez", "carlitos"),
                        UserViewable(10, "Carlos Martinez", "carlitos"),
                        UserViewable(11, "Carlos Martinez", "carlitos"),
                        UserViewable(12, "Carlos Martinez", "carlitos"),
                        UserViewable(13, "Carlos Martinez", "carlitos"),
                        UserViewable(14, "Carlos Martinez", "carlitos"),
                        UserViewable(15, "Carlos Martinez", "carlitos"),
                        UserViewable(16, "Carlos Martinez", "carlitos"),
                        UserViewable(17, "Carlos Martinez", "carlitos"),
                        UserViewable(18, "Carlos Martinez", "carlitos"),
                        UserViewable(19, "Carlos Martinez", "carlitos"),
                        UserViewable(20, "Carlos Martinez", "carlitos")
                    )
                )

                onItemClick = {
                    context.toast(it.realName)
                }

                onFavoriteClick = {
                    context.toast(it.userName)
                }
            }
        }
    }
}