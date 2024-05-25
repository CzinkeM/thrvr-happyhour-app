package com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.presentation.loadingScreen.LoadingScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class HappyHourDetailScreen(
    val serialNumber: Int,
)

@Composable
fun HappyHourDetailScreenWrapper(
    modifier: Modifier = Modifier,
    happyHourSerialNumber: Int,
    navController: NavHostController,
    viewModel: HappyHourDetailViewModel = koinViewModel()
) {
    var happyHour: HappyHourState? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(
        key1 = happyHourSerialNumber,
        block = {
            happyHour = viewModel.getHappyHourBySerialNumber(happyHourSerialNumber)
        }
    )
    AnimatedContent(happyHour, label = "") {
        when(it) {
            null -> {
                LoadingScreen(modifier)
            }
            else -> {
                HappyHourDetailScreen(
                    modifier = modifier,
                    happyHour = happyHour!!,
                    onBackButtonClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HappyHourDetailScreen(
    modifier: Modifier = Modifier,
    happyHour: HappyHourState,
    onBackButtonClick: () -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column {
                        Text(text = happyHour.title)
                        Text(text = happyHour.url)
                        Text(text = happyHour.date)
                        Text(text = happyHour.serialNumber.toString())
                    }
                }
                items(happyHour.chapters) {
                    HappyHourChapterCard(chapter = it)
                }
            }
        }
    }
}