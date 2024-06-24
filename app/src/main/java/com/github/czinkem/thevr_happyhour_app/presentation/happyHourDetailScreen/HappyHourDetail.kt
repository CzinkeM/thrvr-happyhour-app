package com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.domain.utils.ThumbnailProvider
import com.github.czinkem.thevr_happyhour_app.presentation.components.ChapterCard
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HappyHourDetail(
    modifier: Modifier = Modifier,
    happyHour: HappyHourState,
) {
    Surface(
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            item {
                Column {
                    GlideImage(
                        modifier = Modifier,
                        imageModel = {
                            ThumbnailProvider.thumbnailUrl(happyHour.url)
                        },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center,
                        )
                    )
                    Text(text = happyHour.title)
                    Text(text = happyHour.date)
                }
            }
            items(happyHour.chapters) {chapter ->
                ChapterCard(chapterState = chapter)
            }
        }
    }
}