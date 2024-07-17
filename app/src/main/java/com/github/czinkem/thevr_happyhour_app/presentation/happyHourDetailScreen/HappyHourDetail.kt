package com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
                    ) {
                        GlideImage(
                            modifier = Modifier,
                            imageModel = {
                                ThumbnailProvider.thumbnailUrl(happyHour.videoId)
                            },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.Center,
                            )
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                            ),
                        text = happyHour.title,
                        fontSize = 24.sp,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                            ),
                        text = happyHour.date,
                        maxLines = 1,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
                    colors = CardColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.Transparent,
                        disabledContentColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = Color.Transparent,
                    )
                ) {
                    for (chapter in happyHour.chapters) {
                        ChapterCard(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 8.dp
                                ),
                            chapterState = chapter,
                        )
                    }
                }
            }
        }
    }
}