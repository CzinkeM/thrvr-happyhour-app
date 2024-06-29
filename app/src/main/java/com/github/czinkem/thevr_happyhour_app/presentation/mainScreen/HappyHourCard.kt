package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.ui.theme.happyHourBlack
import com.github.czinkem.thevr_happyhour_app.ui.theme.happyHourGray

@Composable
fun HappyHourCard(
    modifier: Modifier = Modifier,
    happyHour: HappyHourState,
    onCardClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onCardClick,
        colors = CardColors(
            containerColor = happyHourGray.copy(0.65f),
            contentColor = happyHourBlack,
            disabledContainerColor = happyHourGray.copy(0.8f),
            disabledContentColor = happyHourBlack,
        )
    ) {
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