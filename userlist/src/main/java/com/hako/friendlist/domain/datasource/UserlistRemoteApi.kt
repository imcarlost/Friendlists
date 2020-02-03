package com.hako.friendlist.domain.datasource

import com.hako.friendlist.model.User
import io.reactivex.Single
import retrofit2.http.GET

interface UserlistRemoteApi {

    @GET("/users")
    fun getUsers(): Single<List<User>>
}