package com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState
import com.github.czinkem.thevr_happyhour_app.domain.utils.openYoutubeIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HappyHourChapterCard(
    modifier: Modifier = Modifier,
    chapter: HappyHourChapterState
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        onClick = {
            context.openYoutubeIntent(chapter.url)
        }
    ) {
        Text(text = chapter.title)
    }
}