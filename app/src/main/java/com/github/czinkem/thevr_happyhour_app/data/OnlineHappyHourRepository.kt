package com.github.czinkem.thevr_happyhour_app.data

class OnlineHappyHourRepository(private val api: HappyHourApi): IHappyHourRepository {

    override fun getAll(): List<HappyHourDto> {
        return api.getAll()
    }
}