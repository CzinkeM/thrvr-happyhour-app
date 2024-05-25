package com.github.czinkem.thevr_happyhour_app.di

import com.github.czinkem.thevr_happyhour_app.data.DataSources
import com.github.czinkem.thevr_happyhour_app.data.HappyHourApi
import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.LocalDataCache
import com.github.czinkem.thevr_happyhour_app.data.OfflineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.OnlineHappyHourRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppKoinModule {

    const val REPOSITORY_OFFLINE_NAME = "offlineRepository"
    const val REPOSITORY_ONLINE_NAME = "onlineRepository"

    val module = module {

        single {
            LocalDataCache(androidApplication())
        }

        single<IHappyHourRepository>(named(REPOSITORY_OFFLINE_NAME)) {
            OfflineHappyHourRepository(get())
        }

        single<IHappyHourRepository>(named(REPOSITORY_ONLINE_NAME)) {
            OnlineHappyHourRepository(get())
        }

        single {
            Retrofit.Builder()
                .baseUrl(DataSources.HAPPYHOUR_LOCALHOST_ROUTE_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HappyHourApi::class.java)
        }
    }
}

