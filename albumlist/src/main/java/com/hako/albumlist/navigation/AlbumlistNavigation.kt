package com.hako.albumlist.navigation

import com.hako.base.navigation.NavigationEvent

sealed class AlbumlistNavigation : NavigationEvent {
    data class ClickedOnAlbum(val albumId: Int) : AlbumlistNavigation()
}
