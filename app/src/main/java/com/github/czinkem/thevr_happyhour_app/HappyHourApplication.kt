package com.github.czinkem.thevr_happyhour_app

import android.app.Application
import com.github.czinkem.thevr_happyhour_app.di.AppKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HappyHourApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HappyHourApplication)
            this.modules(AppKoinModule.module)
        }
    }
}