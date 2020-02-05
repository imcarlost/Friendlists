package com.hako.photolist.feature

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
import com.hako.photolist.R
import com.hako.photolist.model.PhotoViewable
import com.hako.photolist.viewmodel.PhotolistViewmodel
import com.hako.photolist.widget.PhotolistAdapter
import kotlinx.android.synthetic.main.fragment_photolist.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PhotolistFragment : Fragment() {

    private val viewModel: PhotolistViewmodel by viewModel()
    private val listAdapter by lazy { PhotolistAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_photolist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        setObservers()
        // TODO: Get album by bundle
        viewModel.fetchPhotos(1)
    }

    private fun setObservers() {
        viewModel.data.observeNonNull(this) {
            it.either(::handleFetchError, ::handleFetchSuccess)
        }

        viewModel.requestStatus.observeNonNull(this) {
            when (it) {
                RequestStatus.Ready -> {
                    fragment_photolist_error_overlay.gone()
                    fragment_photolist_loading_overlay.gone()
                }
                RequestStatus.Loading -> {
                    fragment_photolist_error_overlay.gone()
                    fragment_photolist_loading_overlay.visible()
                }
                RequestStatus.Errored -> {
                    fragment_photolist_error_overlay.visible()
                    fragment_photolist_loading_overlay.gone()
                }
            }
        }
    }

    private fun handleFetchError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun handleFetchSuccess(photos: List<PhotoViewable>) {
        listAdapter.addAll(photos)
    }

    private fun setRecycler() {
        fragment_photolist_recycler_container.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }
}