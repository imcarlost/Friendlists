package com.hako.userlist.domain.datasource

import com.hako.userlist.model.User
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.get

class UserlistDatasource : KoinComponent, UserlistRemoteApi {

    private val api: UserlistRemoteApi = get()

    override fun getUsers(): Single<List<User>> = api.getUsers()
}