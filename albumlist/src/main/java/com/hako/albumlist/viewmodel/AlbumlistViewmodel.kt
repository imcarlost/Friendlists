package com.hako.albumlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.albumlist.domain.usecase.GetAlbum
import com.hako.albumlist.model.AlbumViewable
import com.hako.base.domain.network.RequestStatus
import com.hako.base.domain.network.RequestStatus.Ready
import com.hako.base.domain.network.RequestStatus.Loading
import com.hako.base.domain.network.RequestStatus.Errored
import com.hako.base.domain.Either
import org.koin.core.KoinComponent
import org.koin.core.get

class AlbumlistViewmodel : ViewModel(), KoinComponent {

    val data = MutableLiveData<Either<Throwable, List<AlbumViewable>>>()
    val requestStatus = MutableLiveData<RequestStatus>()

    private val getUsers: GetAlbum = get()

    fun fetchAlbums(userId: Int) {
        getUsers.execute(
            userId,
            onSuccess = {
                requestStatus.postValue(Ready)
                data.postValue(Either.Right(it))
            },
            onLoading = {
                requestStatus.postValue(Loading)
            },
            onError = {
                requestStatus.postValue(Errored)
                data.postValue(Either.Left(it))
            })
    }
}
