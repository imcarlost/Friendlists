package com.hako.userlist.navigation

import com.hako.base.navigation.NavigationEvent

sealed class UserlistNavigation : NavigationEvent {
    data class ClickedOnUser(val userId: Int) : UserlistNavigation()
}
