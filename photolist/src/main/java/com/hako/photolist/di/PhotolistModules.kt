package com.hako.photolist.di

import androidx.room.Room
import com.hako.base.domain.network.RemoteClient
import com.hako.photolist.BuildConfig
import com.hako.photolist.domain.clients.LocalClient
import com.hako.photolist.domain.datasource.PhotolistRemoteApi
import com.hako.photolist.domain.usecase.GetPhoto
import com.hako.photolist.viewmodel.PhotolistViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoListModules = module {

    single { Room.databaseBuilder(get(), LocalClient::class.java, BuildConfig.DB_NAME).build() }
    factory { get<LocalClient>().photoDao() }

    factory { get<RemoteClient>().getClient(PhotolistRemoteApi::class.java) }
    factory { GetPhoto(get(), get()) }

    viewModel { PhotolistViewmodel() }
}