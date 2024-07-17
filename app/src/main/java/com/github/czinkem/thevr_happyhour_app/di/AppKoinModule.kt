package com.github.czinkem.thevr_happyhour_app.di

import com.github.czinkem.thevr_happyhour_app.data.online.HappyHourApi
import com.github.czinkem.thevr_happyhour_app.data.HappyHourVideoSearchCache
import com.github.czinkem.thevr_happyhour_app.data.online.HttpRoutes
import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.LocalDataCache
import com.github.czinkem.thevr_happyhour_app.data.online.OnlineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen.HappyHourDetailViewModel
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

//        single<IHappyHourRepository>(named(REPOSITORY_OFFLINE_NAME)) {
//            OfflineHappyHourRepository(get())
//        }

        single<IHappyHourRepository>(named(REPOSITORY_ONLINE_NAME)) {
            OnlineHappyHourRepository(
                api = get(),
                cache = HappyHourVideoSearchCache()
            )
        }

        single {
            Retrofit.Builder()
                .baseUrl(HttpRoutes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HappyHourApi::class.java)
        }

        single {
            HappyHourVideoSearchCache()
        }

        viewModel {
            MainScreenViewModel(get(named(REPOSITORY_ONLINE_NAME)))
        }

        viewModel {
            HappyHourDetailViewModel()
        }
    }
}

