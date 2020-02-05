package com.hako.albumlist.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hako.albumlist.R
import com.hako.albumlist.model.AlbumViewable
import com.hako.albumlist.navigation.AlbumlistNavigation
import com.hako.albumlist.viewmodel.AlbumlistViewmodel
import com.hako.albumlist.widget.AlbumlistAdapter
import com.hako.base.domain.network.RequestStatus
import com.hako.base.extensions.gone
import com.hako.base.extensions.observeNonNull
import com.hako.base.extensions.toast
import com.hako.base.extensions.visible
import com.hako.base.navigation.NavigationRouter
import kotlinx.android.synthetic.main.fragment_albumlist.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

const val ALBUMLIST_FRAGMENT_BUNDLE_USER_ID = "ALBUMLIST_FRAGMENT_BUNDLE_USER_ID"

class AlbumlistFragment : Fragment() {

    private val viewModel: AlbumlistViewmodel by viewModel()
    private val listAdapter by lazy { AlbumlistAdapter() }
    private val navigation: NavigationRouter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_albumlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        setObservers()
        doRequest()
    }

    private fun doRequest() {
        arguments?.getInt(ALBUMLIST_FRAGMENT_BUNDLE_USER_ID)?.let { viewModel.fetchAlbums(it) } ?:
        throw UninitializedPropertyAccessException("The UserId is expected but it wasn't provided")
    }

    private fun setObservers() {
        viewModel.data.observeNonNull(this) {
            it.either(::handleFetchError, ::handleFetchSuccess)
        }

        viewModel.requestStatus.observeNonNull(this) {
            when (it) {
                RequestStatus.Ready -> {
                    fragment_albumlist_error_overlay.gone()
                    fragment_albumlist_loading_overlay.gone()
                }
                RequestStatus.Loading -> {
                    fragment_albumlist_error_overlay.gone()
                    fragment_albumlist_loading_overlay.visible()
                }
                RequestStatus.Errored -> {
                    fragment_albumlist_error_overlay.visible()
                    fragment_albumlist_loading_overlay.gone()
                }
            }
        }
    }

    private fun handleFetchError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun handleFetchSuccess(users: List<AlbumViewable>) {
        listAdapter.addAll(users)
    }

    private fun setRecycler() {
        fragment_albumlist_recycler_container.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter.apply {
                onItemClick = {
                    navigation.sendNavigation(AlbumlistNavigation.ClickedOnAlbum(it.id))
                }
            }
        }
    }
}