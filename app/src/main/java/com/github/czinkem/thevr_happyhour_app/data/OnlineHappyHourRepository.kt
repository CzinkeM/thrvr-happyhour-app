package com.github.czinkem.thevr_happyhour_app.data

import android.util.Log
import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourDto
import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourVideoDto
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

private const val TAG = "OnlineHappyHourRepository"
class OnlineHappyHourRepository(
    private val api: HappyHourApi,
    private val cache: SearchCache<HappyHourVideoDto>
): IHappyHourRepository {
    private var lastPage = 0

    var happyHours = MutableStateFlow<List<HappyHourVideoDto>>(emptyList())
    override fun happyHours() = happyHours.asStateFlow()

    // TODO: Successfully download all the hh now i should display it
    private fun loadPage(
        targetPage: String
    ): HappyHourDto {
        val response = api.getPage(
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("srcTag", "")
                .addFormDataPart("page", targetPage)
                .build()
        ).execute()

        if(response.isSuccessful) {
            response.body()?.let { happyHourDto ->
                return happyHourDto
            } ?: throw NullPointerException()
        }else {
            Log.d(TAG, "onResponse response not succesfull")
            throw HttpException(response)
        }
    }
    override fun loadAllHappyHour() {
        var targetPage = "0"
        var isMoreHappyHourAvailable = true
        val hhList = mutableListOf<HappyHourVideoDto>()
        while (isMoreHappyHourAvailable) {
            val dto = loadPage(
                targetPage = targetPage,
//                onSuccess = {
//                    if(it.hhVideos.isEmpty()) {
//                        isMoreHappyHourAvailable = false
//                    } else {
//                        targetPage = it.page.toString()
//                        hhList + it.hhVideos
//                    }
//                },
//                onError = {
//                    throw it ?: NullPointerException()
//                }
            )
            if(dto.hhVideos.isEmpty()) {
                isMoreHappyHourAvailable = false
            } else {
                targetPage = dto.page.toString()
                hhList.addAll(dto.hhVideos)
                Log.d(TAG, "loadAllHappyHour: add $dto")
            }
        }
        val asd = hhList
        cache.cache(hhList)
        happyHours.update { hhList }
        val asdasd = happyHours.value
    }

    fun loadNextPage() {
        api
            .getPage(
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("srcTag", "")
                    .addFormDataPart("page", lastPage.toString())
                    .build()
            )
            .enqueue(
                object : Callback<HappyHourDto> {
                    override fun onResponse(call: Call<HappyHourDto>, response: Response<HappyHourDto>) {
                        if(response.isSuccessful) {
                            response.body()?.let { happyHourDto ->
                                happyHourDto.hhVideos.takeIf { it.isNotEmpty() }?.let { hhVideos ->
                                    happyHours.update {
                                        it + hhVideos
                                    }
                                    cache.cache(happyHours.value)
                                    lastPage = happyHourDto.page ?: 0
                                }
                            }
//                            val happyHourDto = response.body()
//                            if(!happyHourDto?.hhVideos.isNullOrEmpty()) {
//                                happyHours.update {
//                                    val asd = mutableListOf<HappyHourVideoDto>()
//                                    asd.addAll(it)
//                                    asd.addAll(happyHourDto!!.hhVideos)
//                                    asd
//                                }
//                                lastPage =  happyHourDto?.page ?:  0
//                            }
                        }else {
                            Log.d(TAG, "onResponse response not succesfull")
                        }
                    }

                    override fun onFailure(call: Call<HappyHourDto>, error: Throwable) {
                        Log.w(TAG, "onFailure: ", error)
                    }
                }
            )
    }

    fun getHappyHourByFreeText(searchedString: String): List<HappyHourVideoDto> {
        val searchResult = cache.search(searchedString)
        return happyHours.value.filter { hh -> hh.id in searchResult }
    }
}