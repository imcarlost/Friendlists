package com.hako.friendlists

import android.app.Application
import com.hako.albumlist.di.albumListModules
import com.hako.userlist.di.userlistModules
import com.hako.friendlists.di.appModules
import com.hako.photolist.di.photoListModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@Suppress("unused")
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupDi()
    }

    private fun setupLogger() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@MainApplication)

            modules(
                listOf(
                    appModules,
                    userlistModules,
                    albumListModules,
                    photoListModules
                )
            )
        }
    }
}