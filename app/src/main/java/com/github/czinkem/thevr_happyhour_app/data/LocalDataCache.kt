package com.github.czinkem.thevr_happyhour_app.data

import android.content.Context
import com.github.czinkem.thevr_happyhour_app.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter


class LocalDataCache(private val context: Context) {

    fun json(): String {
        val inputStream = context.resources.openRawResource(R.raw.happyhours)
        val writer = StringWriter()
        try {
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var line = reader.readLine()
            while (line != null) {
                writer.write(line)
                line = reader.readLine()
            }
        } catch (e: Exception) {
            throw e
        } finally {
            try {
                inputStream.close()
            } catch (e: Exception) {
                throw e
            }
        }

        return writer.toString()
    }
}