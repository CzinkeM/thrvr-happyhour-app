package com.github.czinkem.thevr_happyhour_app.di

import androidx.room.Room
import com.github.czinkem.thevr_happyhour_app.data.HappyHourVideoSearchCache
import com.github.czinkem.thevr_happyhour_app.data.LocalDataCache
import com.github.czinkem.thevr_happyhour_app.data.offline.HappyHourDatabase
import com.github.czinkem.thevr_happyhour_app.data.offline.OfflineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.online.HappyHourApi
import com.github.czinkem.thevr_happyhour_app.data.online.HttpRoutes
import com.github.czinkem.thevr_happyhour_app.data.online.OnlineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen.HappyHourDetailViewModel
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppKoinModule {

    val module = module {

        single {
            LocalDataCache(androidApplication())
        }

        single {
            Room.databaseBuilder(
                context = androidApplication(),
                klass = HappyHourDatabase::class.java,
                "happyhours.db",
            ).build()
        }

        single {
            get<HappyHourDatabase>().dao
        }

        single {
            OfflineHappyHourRepository(
                dao = get()
            )
        }

        single {
            OnlineHappyHourRepository(
                api = get(),
                searchCache = HappyHourVideoSearchCache(),
            )
        }

        single {
            // TODO: Currently we don't handle if there is no internet connection so in these cases the app crashes
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
            MainScreenViewModel()
        }

        viewModel {
            HappyHourDetailViewModel()
        }
    }
}

