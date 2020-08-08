package com.hako.userlist.feature

class UserlistFragment : BaseUserlistFragment() {
    override fun doRequest() = viewModel.fetchUsers()

    override fun shouldShowFabButton() = true
}
