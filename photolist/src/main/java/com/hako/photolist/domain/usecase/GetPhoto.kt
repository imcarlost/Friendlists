package com.hako.photolist.domain.usecase

import com.hako.photolist.domain.datasource.PhotoDao
import com.hako.photolist.domain.datasource.PhotolistRemoteApi
import com.hako.photolist.model.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetPhoto(private val dao: PhotoDao, private val api: PhotolistRemoteApi) {

    fun execute(
        albumId: Int,
        onSuccess: (List<Photo>) -> Unit,
        onError: (Throwable) -> Unit,
        onLoading: () -> Unit
    ) {
        Single.fromCallable { dao.getPhotos(albumId) }
            .subscribeOn(Schedulers.io())
            .doOnError { onError(it) }
            .doOnSuccess { dbAlbum ->
                if (dbAlbum.isEmpty() || dbAlbum.count() == 0) {
                    api.getPhotos(albumId)
                        .doOnSuccess {
                            dao.saveAll(it.map { album -> album.toPhotoEntity() })
                            onSuccess(dao.getPhotos(albumId).map { album -> album.toPhotoViewable() })
                        }
                        .doOnSubscribe { onLoading() }
                        .subscribeOn(Schedulers.io())
                        .subscribe({}, { onError(it) })
                } else {
                    onSuccess(dbAlbum.map { it.toPhotoViewable() })
                }
            }
            .subscribe()
    }
}
