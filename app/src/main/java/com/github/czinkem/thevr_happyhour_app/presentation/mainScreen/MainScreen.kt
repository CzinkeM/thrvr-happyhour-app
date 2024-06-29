package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
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
    val isHappyHoursLoading by viewModel.happyHoursLoading.collectAsStateWithLifecycle()

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
        isHappyHoursLoading = isHappyHoursLoading,
        onAnimationProgressChange = { isCompleted ->
            viewModel.onAnimationProgressChange(isCompleted)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSettingsIconClick: () -> Unit,
    happyHours: List<HappyHourState>,
    isHappyHoursLoading: Boolean,
    onAnimationProgressChange: (isCompleted: Boolean) -> Unit,
) {

    var isSearchDialogShows by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier,
    ) {
        AnimatedVisibility(visible = isSearchDialogShows) {
            Dialog(onDismissRequest = { isSearchDialogShows = false }) {
                Text(text = "Ez a search dialog")
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



            AnimatedContent(
                targetState = isHappyHoursLoading,
                label = "isHappyHoursLoading"
            ) { isLoading ->
                when(isLoading) {
                    true -> {
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.happy_hour_coffee_gray_loading))
                        val progress by animateLottieCompositionAsState(composition)

                        LaunchedEffect(
                            key1 = progress,
                            block = {
                                onAnimationProgressChange(progress > 0.5f)
                            }
                        )
                        Column(
                            modifier = Modifier.align(CenterHorizontally),
                            horizontalAlignment = CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            LottieAnimation(
                                modifier = Modifier,
                                composition = composition,
                                progress = { progress },
                            )
                            AnimatedVisibility(visible = progress > 0.01) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Happy Hour",
                                    textAlign = TextAlign.Center,
                                    fontSize = 32.sp,
                                )
                            }
                        }

                    }
                    false -> {
                        NavigableListDetailPaneScaffold(
                            modifier = Modifier.fillMaxSize(),
                            navigator = navigator,
                            listPane = {

                                HappyHourList(
                                    modifier = Modifier.fillMaxSize(),
                                    happyHours = happyHours,
                                    onHappyHourCardClick = { happyHour ->
                                        navigator.navigateTo(
                                            pane = ListDetailPaneScaffoldRole.Detail,
                                            content = happyHour
                                        )
                                    },
                                    onFloatingActionButtonClick = {
                                        isSearchDialogShows = true
                                    }
                                )

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
        }
    }
}