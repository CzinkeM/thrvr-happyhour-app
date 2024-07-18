package com.github.czinkem.thevr_happyhour_app.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState

@Composable
fun ChapterCard(
    modifier: Modifier = Modifier,
    chapterState: HappyHourChapterState,
) {
    val context = LocalContext.current

    Card(
        modifier = modifier,
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(chapterState.uri))
            context.startActivity(intent)
        },
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.secondary,
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.weight(4f),
                text = chapterState.timeStamp
            )
            Text(
                modifier = Modifier.weight(10f),
                text = chapterState.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}