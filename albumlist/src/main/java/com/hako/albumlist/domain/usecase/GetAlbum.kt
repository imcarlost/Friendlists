package com.hako.albumlist.domain.usecase

import com.hako.albumlist.domain.datasource.AlbumlistRemoteApi
import com.hako.albumlist.model.AlbumViewable
import com.hako.albumlist.model.toAlbumEntity
import com.hako.albumlist.model.toAlbumViewable
import com.hako.base.domain.database.dao.AlbumDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.get

class GetAlbum(private val dao: AlbumDao) : KoinComponent {

    private val api: AlbumlistRemoteApi = get()

    fun execute(
        userId: Int,
        onSuccess: (List<AlbumViewable>) -> Unit,
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
