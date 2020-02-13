package com.hako.albumlist.domain.datasource

import com.hako.albumlist.model.AlbumRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumlistRemoteApi {

    @GET("/albums")
    fun getAlbums(
        @Query("userId") userId: Int
    ): Single<List<AlbumRemote>>
}