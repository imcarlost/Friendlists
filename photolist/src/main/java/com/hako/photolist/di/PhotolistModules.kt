package com.hako.photolist.di

import com.hako.base.domain.network.RemoteClient
import com.hako.photolist.domain.datasource.PhotolistRemoteApi
import com.hako.photolist.domain.usecase.GetPhoto
import com.hako.photolist.viewmodel.PhotolistViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoListModules = module {
    factory { get<RemoteClient>().getClient(PhotolistRemoteApi::class.java) }
    factory { GetPhoto(get()) }

    viewModel { PhotolistViewmodel() }
}