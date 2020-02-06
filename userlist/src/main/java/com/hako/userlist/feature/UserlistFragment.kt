package com.hako.userlist.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hako.base.domain.network.RequestStatus
import com.hako.base.extensions.gone
import com.hako.base.extensions.observeNonNull
import com.hako.base.extensions.visible
import com.hako.base.navigation.NavigationRouter
import com.hako.base.navigation.ShowFabButton
import com.hako.userlist.model.UserViewable
import com.hako.userlist.viewmodel.UserlistViewmodel
import com.hako.userlist.widget.UserlistAdapter
import com.hako.friendlist_userlist.R
import com.hako.userlist.navigation.UserlistNavigation
import kotlinx.android.synthetic.main.fragment_userlist.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

open class UserlistFragment : Fragment(), ShowFabButton {

    val viewModel: UserlistViewmodel by viewModel()
    private val listAdapter by lazy { UserlistAdapter() }
    private val navigation: NavigationRouter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_userlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        setObservers()
        doRequest()
    }

    open fun doRequest() {
        viewModel.fetchUsers()
    }

    override fun shouldShowFabButton() = true

    override fun fabButtonPressed(): () -> Unit = {
        navigation.sendNavigation(UserlistNavigation.ClickedOnFab)
    }

    private fun setObservers() {
        viewModel.userList.observeNonNull(this) {
            it.either(::handleFetchError, ::handleFetchSuccess)
        }

        viewModel.requestStatus.observeNonNull(this) {
            when (it) {
                RequestStatus.Ready -> {
                    fragment_userlist_error_overlay.gone()
                    fragment_userlist_loading_overlay.gone()
                    fragment_userlist_empty_overlay.gone()
                }
                RequestStatus.Loading -> {
                    fragment_userlist_error_overlay.gone()
                    fragment_userlist_loading_overlay.visible()
                    fragment_userlist_empty_overlay.gone()
                }
                RequestStatus.Errored -> {
                    fragment_userlist_error_overlay.visible()
                    fragment_userlist_loading_overlay.gone()
                    fragment_userlist_empty_overlay.gone()
                }
                RequestStatus.Empty -> {
                    fragment_userlist_error_overlay.gone()
                    fragment_userlist_loading_overlay.gone()
                    fragment_userlist_empty_overlay.visible()
                }
            }
        }

        viewModel.emptyMessage.observeNonNull(this) {
            fragment_userlist_empty_overlay.setLabel(it)
        }
    }

    private fun handleFetchError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun handleFetchSuccess(users: List<UserViewable>) {
        listAdapter.addAll(users)
    }

    private fun setRecycler() {
        fragment_userlist_recycler_container.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter.apply {
                onItemClick = {
                    navigation.sendNavigation(UserlistNavigation.ClickedOnUser(it.id, it.realName))
                }

                onFavoriteClick = {
                    viewModel.updateUserFavoriteStatus(it.id, !it.isFavorite)
                }
            }
        }
    }
}