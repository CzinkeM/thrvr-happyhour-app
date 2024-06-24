package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.czinkem.thevr_happyhour_app.R
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen.HappyHourDetail
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
    LaunchedEffect(
        key1 = Unit,
        block = {
            viewModel.initHappyHours()
        }
    )

    MainScreen(
        modifier = modifier,
        onSettingsIconClick = {  },
        happyHours = happyHours,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSettingsIconClick: () -> Unit,
    happyHours: List<HappyHourState>,
) {

    var isSearchDialogShows by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier,
    ) {
        AnimatedVisibility(visible = isSearchDialogShows) {
            Dialog(onDismissRequest = { isSearchDialogShows = false }) {
                Text(text = "Ez a seatch dialog")
            }
        }

        val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
        Column {
            AnimatedContent(targetState = navigator.currentDestination?.pane, label = "") { pane ->
                when(pane) {
                    ListDetailPaneScaffoldRole.Detail -> {
                        val content = navigator.currentDestination?.content?.let { it as HappyHourState }
                        TopAppBar(
                            title = {
                                Text(text = "HappyHour - ${content?.serialNumber}")
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        navigator.navigateBack()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                        )
                    }
                    else -> {
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
                    }
                }
            }

            NavigableListDetailPaneScaffold(
                modifier = Modifier.fillMaxSize(),
                navigator = navigator,
                listPane = {
                    // TODO: Create a separated composable for this
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier.align(Alignment.Center).fillMaxSize().blur(4.dp),
                            painter = painterResource(id = R.drawable.topo),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            alignment = Alignment.Center,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(happyHours) {
                                HappyHourCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .padding(bottom = 8.dp),
                                    happyHour = it,
                                    onCardClick = {
                                        navigator.navigateTo(
                                            pane = ListDetailPaneScaffoldRole.Detail,
                                            content = it
                                        )
                                    }
                                )
                            }
                        }
                        FloatingActionButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp),
                            onClick = { isSearchDialogShows = true},
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    }
                },
                detailPane = {
                    val content = navigator.currentDestination?.content?.let { it as HappyHourState }
                    if (content != null) {
                        HappyHourDetail(
                            modifier = Modifier.fillMaxSize(),
                            happyHour = content,
                        )
                    }
                }
            )
        }
    }
}