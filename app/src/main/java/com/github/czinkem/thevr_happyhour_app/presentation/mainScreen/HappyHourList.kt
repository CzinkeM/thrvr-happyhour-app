package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.presentation.components.TopologicalBackground

@Composable
fun HappyHourList(
    modifier: Modifier = Modifier,
    happyHours: List<HappyHourState>,
    onHappyHourCardClick: (HappyHourState) -> Unit,
    onFloatingActionButtonClick: () -> Unit,
    isListFiltered: Boolean,
    onClearFilterClick: () -> Unit,
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        TopologicalBackground(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
        AnimatedVisibility(visible = happyHours.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(happyHours) {
                    HappyHourCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp),
                        happyHour = it,
                        onCardClick = { onHappyHourCardClick(it) }
                    )
                }
            }
        }
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            visible = isListFiltered
        ) {
            FloatingActionButton(
                onClick = onClearFilterClick,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Clear Search")
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onFloatingActionButtonClick,
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.primary,
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}