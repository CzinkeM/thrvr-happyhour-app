package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Row {
            Row {
                Icon(imageVector = Icons.Default.Coffee, contentDescription = null)
                Text(text = happyHour.serialNumber.toString())
            }
            Text(text = happyHour.title)
            Text(text = happyHour.date)
        }
    }
}