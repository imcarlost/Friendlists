package com.hako.friendlist.di

import com.hako.base.domain.network.RemoteClient
import com.hako.friendlist.domain.datasource.UserlistDatasource
import com.hako.friendlist.domain.datasource.UserlistRemoteApi
import com.hako.friendlist.domain.usecase.GetUsers
import com.hako.friendlist.viewmodel.UserlistViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userlistModules = module {
    factory { get<RemoteClient>().getClient(UserlistRemoteApi::class.java) }
    factory { UserlistDatasource() }
    factory { GetUsers(get()) }

    viewModel { UserlistViewmodel() }
}