package com.hako.friendlists.di

import com.hako.base.domain.network.RemoteClient
import com.hako.base.navigation.NavigationRouter
import com.hako.friendlists.BuildConfig
import com.hako.friendlists.viewmodel.NavigationViewmodel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    // Retrofit
    single { RemoteClient(BuildConfig.BASE_ENDPOINT) }

    // Picasso
    single { Picasso.get() }

    // Navigation
    single { NavigationRouter() }
    viewModel { NavigationViewmodel() }
}