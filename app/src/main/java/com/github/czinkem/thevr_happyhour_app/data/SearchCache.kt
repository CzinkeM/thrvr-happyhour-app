package com.github.czinkem.thevr_happyhour_app.data

interface SearchCache<T> {
    fun cache(happyHours: List<T>)

    fun search(searchedString: String): List<Int>
}