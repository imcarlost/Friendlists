package com.hako.albumlist.di

import com.hako.albumlist.domain.datasource.AlbumlistRemoteApi
import com.hako.albumlist.domain.usecase.GetAlbum
import com.hako.albumlist.viewmodel.AlbumlistViewmodel
import com.hako.base.domain.network.RemoteClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val albumListModules = module {
    factory { get<RemoteClient>().getClient(AlbumlistRemoteApi::class.java) }
    factory { GetAlbum(get()) }

    viewModel { AlbumlistViewmodel() }
}