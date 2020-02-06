package com.hako.userlist.navigation

import com.hako.base.navigation.NavigationEvent

sealed class UserlistNavigation : NavigationEvent {
    data class ClickedOnUser(val userId: Int, val userName: String) : UserlistNavigation()
    object ClickedOnFab : UserlistNavigation()
}
