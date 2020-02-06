package com.hako.base.navigation

interface NavigationEvent

interface NavigationController {
    fun sendNavigation(event: NavigationEvent)
}