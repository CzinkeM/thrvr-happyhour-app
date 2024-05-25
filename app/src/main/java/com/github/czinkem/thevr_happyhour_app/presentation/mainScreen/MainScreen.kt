package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreenWrapper(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel()
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSettingsIconClick: () -> Unit,
    happyHours: List<HappyHour>,
    onHappyHoursClick: (HappyHour) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(text = "Happy Hours")
                },
                actions = {
                    IconButton(onClick = onSettingsIconClick) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription =null)
                    }
                }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {}
        }
    }
}