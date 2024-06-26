package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.ui.theme.happyHourBlack
import com.github.czinkem.thevr_happyhour_app.ui.theme.happyHourGray

@Composable
fun HappyHourDateCard(
    modifier: Modifier = Modifier,
    date: String,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            containerColor = happyHourBlack,
            contentColor = happyHourGray,
            disabledContainerColor = happyHourBlack,
            disabledContentColor = happyHourGray,
        )
    ) {
        Row(
            Modifier.padding(horizontal = 4.dp)
        ) {
            Icon(
                modifier = Modifier.scale(.9f).align(CenterVertically),
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                text = date,
                textAlign = TextAlign.Center,
            )
        }
    }
}