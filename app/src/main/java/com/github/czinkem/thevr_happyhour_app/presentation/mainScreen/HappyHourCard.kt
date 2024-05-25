package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HappyHourCard(
    modifier: Modifier = Modifier,
    happyHour: HappyHourState,
    onCardClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onCardClick) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            HappyHourSerialNumber(
                serialNumber = happyHour.serialNumber
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = happyHour.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                HappyHourDateCard(
                    modifier = Modifier.padding(top = 4.dp),
                    date = happyHour.date
                )
            }
        }
    }
}