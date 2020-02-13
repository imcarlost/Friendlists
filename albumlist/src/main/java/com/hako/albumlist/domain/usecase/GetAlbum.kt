package com.hako.albumlist.domain.usecase

import com.hako.albumlist.domain.datasource.AlbumDao
import com.hako.albumlist.domain.datasource.AlbumlistRemoteApi
import com.hako.albumlist.model.Album
import com.hako.albumlist.model.toAlbumEntity
import com.hako.albumlist.model.toAlbumViewable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetAlbum(private val dao: AlbumDao, private val api: AlbumlistRemoteApi) {

    fun execute(
        userId: Int,
        onSuccess: (List<Album>) -> Unit,
        onError: (Throwable) -> Unit,
        onLoading: () -> Unit
    ) {
        Single.fromCallable { dao.getAlbums(userId) }
            .subscribeOn(Schedulers.io())
            .doOnError { onError(it) }
            .doOnSuccess { dbAlbum ->
                if (dbAlbum.isEmpty() || dbAlbum.count() == 0) {
                    api.getAlbums(userId)
                        .doOnSuccess {
                            dao.saveAll(it.map { album -> album.toAlbumEntity() })
                            onSuccess(dao.getAlbums(userId).map { album -> album.toAlbumViewable() })
                        }
                        .doOnSubscribe { onLoading() }
                        .subscribeOn(Schedulers.io())
                        .subscribe({}, { onError(it) })
                } else {
                    onSuccess(dbAlbum.map { it.toAlbumViewable() })
                }
            }
            .subscribe()
    }
}
