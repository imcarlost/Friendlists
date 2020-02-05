package com.hako.photolist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.base.domain.network.RequestStatus
import com.hako.base.domain.network.RequestStatus.Ready
import com.hako.base.domain.network.RequestStatus.Loading
import com.hako.base.domain.network.RequestStatus.Errored
import com.hako.base.domain.Either
import com.hako.photolist.domain.usecase.GetPhoto
import com.hako.photolist.model.PhotoViewable
import org.koin.core.KoinComponent
import org.koin.core.get

class PhotolistViewmodel : ViewModel(), KoinComponent {

    val data = MutableLiveData<Either<Throwable, List<PhotoViewable>>>()
    val requestStatus = MutableLiveData<RequestStatus>()

    private val getPhoto: GetPhoto = get()

    fun fetchPhotos(albumId: Int) {
        getPhoto.execute(
            albumId,
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
