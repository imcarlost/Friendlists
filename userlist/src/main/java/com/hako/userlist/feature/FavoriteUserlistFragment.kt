package com.hako.userlist.feature

class FavoriteUserlistFragment : BaseUserlistFragment() {
    override fun doRequest() = viewModel.fetchFavoriteUsers()

    override fun shouldShowFabButton() = false
}
