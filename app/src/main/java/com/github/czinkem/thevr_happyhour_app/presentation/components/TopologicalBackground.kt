package com.github.czinkem.thevr_happyhour_app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.czinkem.thevr_happyhour_app.R

@Composable
fun TopologicalBackground(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.blur(4.dp),
        painter = painterResource(id = R.drawable.topo),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center,
        colorFilter = ColorFilter.tint(Color.White)
    )
}