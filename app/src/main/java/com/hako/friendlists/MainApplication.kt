package com.hako.friendlists

import android.app.Application
import com.hako.base.di.baseModule
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
                    baseModule
                )
            )
        }
    }
}