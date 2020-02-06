package com.hako.base.navigation

interface ShowFabButton {
    fun shouldShowFabButton(): Boolean

    fun fabButtonPressed(): () -> Unit = {}
}