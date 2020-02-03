package com.hako.friendlist.domain.datasource

import com.hako.friendlist.model.User
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.get

class UserlistDatasource : KoinComponent, UserlistRemoteApi {

    private val api: UserlistRemoteApi = get()

    override fun getUsers(): Single<List<User>> = api.getUsers()
}