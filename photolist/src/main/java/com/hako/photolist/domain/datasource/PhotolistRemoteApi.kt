package com.hako.photolist.domain.datasource

import com.hako.photolist.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotolistRemoteApi {

    @GET("/photos")
    fun getPhotos(
        @Query("albumId") albumId: Int
    ): Single<List<Photo>>
}