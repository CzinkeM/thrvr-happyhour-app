package com.github.czinkem.thevr_happyhour_app

import android.app.Application
import com.github.czinkem.thevr_happyhour_app.di.appModule
import org.koin.core.context.startKoin

class HappyHourApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            this.modules(appModule)
        }
    }
}