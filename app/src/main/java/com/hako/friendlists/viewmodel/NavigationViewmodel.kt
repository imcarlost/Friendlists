package com.hako.friendlists.viewmodel

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.albumlist.feature.ALBUMLIST_FRAGMENT_BUNDLE_USER_ID
import com.hako.albumlist.navigation.AlbumlistNavigation
import com.hako.base.extensions.buildNavigation
import com.hako.base.navigation.NavigationEvent
import com.hako.friendlists.R
import com.hako.photolist.feature.PHOTOLIST_FRAGMENT_BUNDLE_ALBUM_ID
import com.hako.userlist.navigation.UserlistNavigation

// This sets the fragment title, it's referenced in every navigation
const val FRAGMENT_TITLE = "actionTitle"

class NavigationViewmodel : ViewModel() {

    val navigate = MutableLiveData<Pair<@IdRes Int, Bundle>>()

    fun onNavigationEvent(event: NavigationEvent) {
        when (event) {
            is UserlistNavigation -> handleUserlistNavigation(event)
            is AlbumlistNavigation -> handleAlbumlistNavigation(event)
            else -> throw NoWhenBranchMatchedException("Undefined navigation event parent")
        }
    }

    private fun handleUserlistNavigation(event: UserlistNavigation) {
        when (event) {
            is UserlistNavigation.ClickedOnUser -> navigate.postValue(
                buildNavigation(R.id.action_userlistFragment_to_albumlistFragment, Bundle().apply {
                    putInt(ALBUMLIST_FRAGMENT_BUNDLE_USER_ID, event.userId)
                    putString(FRAGMENT_TITLE, event.userName)
                })
            )
            is UserlistNavigation.ClickedOnFab -> navigate.postValue(
                buildNavigation(R.id.action_userlistFragment_to_favoriteUserlistFragment)
            )
        }
    }

    private fun handleAlbumlistNavigation(event: AlbumlistNavigation) {
        when (event) {
            is AlbumlistNavigation.ClickedOnAlbum -> navigate.postValue(
                buildNavigation(R.id.action_albumlistFragment_to_photolistFragment, Bundle().apply {
                    putInt(PHOTOLIST_FRAGMENT_BUNDLE_ALBUM_ID, event.albumId)
                })
            )
        }
    }
}