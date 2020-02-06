package com.hako.userlist.di

import com.hako.base.domain.network.RemoteClient
import com.hako.userlist.domain.datasource.UserlistRemoteApi
import com.hako.userlist.domain.usecase.GetFavoriteUsers
import com.hako.userlist.domain.usecase.GetUsers
import com.hako.userlist.domain.usecase.SetFavoriteStatus
import com.hako.userlist.viewmodel.UserlistViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userlistModules = module {
    factory { get<RemoteClient>().getClient(UserlistRemoteApi::class.java) }
    factory { GetUsers(get()) }
    factory { GetFavoriteUsers(get()) }
    factory { SetFavoriteStatus(get()) }

    viewModel { UserlistViewmodel() }
}