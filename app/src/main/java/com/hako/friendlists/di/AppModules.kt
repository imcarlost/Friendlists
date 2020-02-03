package com.hako.friendlists.di

import androidx.room.Room
import com.hako.base.domain.database.DatabaseClient
import com.hako.base.domain.network.RemoteClient
import com.hako.friendlists.BuildConfig
import org.koin.dsl.module

val appModules = module {
    // Room database
    single { Room.databaseBuilder(get(), DatabaseClient::class.java, BuildConfig.DB_NAME).build() }
    factory { get<DatabaseClient>().userDao() }
    factory { get<DatabaseClient>().albumDao() }
    factory { get<DatabaseClient>().photoDao() }

    // Retrofit
    single { RemoteClient(BuildConfig.BASE_ENDPOINT) }
}