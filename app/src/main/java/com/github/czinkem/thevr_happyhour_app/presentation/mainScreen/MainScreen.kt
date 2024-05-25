package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen.HappyHourDetailScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object MainScreen
@Composable
fun MainScreenWrapper(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val happyHours by viewModel.happyHours.collectAsStateWithLifecycle()
    MainScreen(
        modifier = modifier,
        onSettingsIconClick = {  },
        happyHours = happyHours,
        onHappyHoursClick ={
            navController.navigate(HappyHourDetailScreen(it.title))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSettingsIconClick: () -> Unit,
    happyHours: List<HappyHourState>,
    onHappyHoursClick: (HappyHourState) -> Unit,
) {

    var isSearchDialogShows by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        modifier = modifier
    ) {
        AnimatedVisibility(visible = isSearchDialogShows) {
            Dialog(onDismissRequest = { isSearchDialogShows = false }) {
                Text(text = "Ez a seatch dialog")
            }
        }

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
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    items(happyHours) {
                        HappyHourCard(
                            modifier = Modifier,
                            happyHour = it,
                            onCardClick = { onHappyHoursClick(it) }
                        )
                    }
                }

                FloatingActionButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = { isSearchDialogShows = true}
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        }
    }
}