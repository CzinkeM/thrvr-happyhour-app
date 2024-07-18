package com.github.czinkem.thevr_happyhour_app.data.online

import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.SearchCache
import com.github.czinkem.thevr_happyhour_app.data.offline.HappyHourDao
import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourDto
import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourVideoDto
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toHappyHourList
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import okhttp3.MultipartBody
import retrofit2.HttpException

class OnlineHappyHourRepository(
    private val api: HappyHourApi,
    private val searchCache: SearchCache<HappyHourVideoDto>,
    private val dao: HappyHourDao,
    ): IHappyHourRepository {
    var happyHours = MutableStateFlow<List<HappyHourVideoDto>>(emptyList())

    override fun happyHours(): Flow<List<HappyHour>> {
        return happyHours.map { it.toHappyHourList() }
    }

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
            throw HttpException(response)
        }
    }
    override suspend fun loadAllHappyHour() {
        // TODO: empty stringtől ("","0","8",..) indul és mindig adunk hozzá 8-t
        var targetPage = "0"
        var isMoreHappyHourAvailable = true
        val hhList = mutableListOf<HappyHourVideoDto>()
        while (isMoreHappyHourAvailable) {
            val dto = loadPage(
                targetPage = targetPage,
            )
            if(dto.hhVideos.isEmpty()) {
                isMoreHappyHourAvailable = false
            } else {
                targetPage = dto.page.toString()
                hhList.addAll(dto.hhVideos)
            }
        }
        searchCache.cache(hhList)
//        dao.upsertHappyHour(
//            *hhList.toDatabaseEntityList().toTypedArray()
//        )
        happyHours.update { hhList }
    }

    fun getHappyHourByFreeText(searchedString: String): List<HappyHourVideoDto> {
        val searchResult = searchCache.search(searchedString)
        return happyHours.value.filter { hh -> hh.id in searchResult }
    }
}