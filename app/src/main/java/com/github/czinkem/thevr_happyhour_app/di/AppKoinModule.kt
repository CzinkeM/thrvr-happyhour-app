package com.github.czinkem.thevr_happyhour_app.di

import com.github.czinkem.thevr_happyhour_app.data.DataSources
import com.github.czinkem.thevr_happyhour_app.data.HappyHourApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(DataSources.HAPPYHOUR_LOCALHOST_ROUTE_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HappyHourApi::class.java)
    }
}