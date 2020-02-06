package com.hako.userlist.feature

class FavoriteUserlistFragment : UserlistFragment() {
    override fun doRequest() {
        viewModel.fetchFavoriteUsers()
    }

    override fun shouldShowFabButton() = false
}