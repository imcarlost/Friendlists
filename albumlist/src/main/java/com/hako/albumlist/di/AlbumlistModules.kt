package com.hako.albumlist.di

import androidx.room.Room
import com.hako.albumlist.BuildConfig
import com.hako.albumlist.domain.clients.LocalClient
import com.hako.albumlist.domain.datasource.AlbumlistRemoteApi
import com.hako.albumlist.domain.usecase.GetAlbum
import com.hako.albumlist.viewmodel.AlbumlistViewmodel
import com.hako.base.domain.network.RemoteClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val albumListModules = module {

    single { Room.databaseBuilder(get(), LocalClient::class.java, BuildConfig.DB_NAME).build() }
    factory { get<LocalClient>().albumDao() }

    factory { get<RemoteClient>().getClient(AlbumlistRemoteApi::class.java) }
    factory { GetAlbum(get(), get()) }

    viewModel { AlbumlistViewmodel() }
}