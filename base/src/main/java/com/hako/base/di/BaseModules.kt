package com.hako.base.di

import androidx.room.Room
import com.hako.base.BuildConfig
import com.hako.base.room.BaseDatabase
import org.koin.dsl.module

val baseModule = module {

    // Room database
    single { Room.databaseBuilder(get(), BaseDatabase::class.java, BuildConfig.DB_NAME).build() }
    factory { get<BaseDatabase>().userDao() }
    factory { get<BaseDatabase>().albumDao() }
    factory { get<BaseDatabase>().photoDao() }

}