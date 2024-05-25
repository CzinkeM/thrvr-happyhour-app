package com.github.czinkem.thevr_happyhour_app.domain.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openYoutubeIntent(uri: String) {
    val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    try {
        this.startActivity(youtubeIntent)
    } catch (ex: ActivityNotFoundException) {
        // TODO:
    }
}