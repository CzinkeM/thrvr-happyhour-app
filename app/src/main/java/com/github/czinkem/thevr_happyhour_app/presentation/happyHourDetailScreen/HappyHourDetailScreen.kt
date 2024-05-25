package com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class HappyHourDetailScreen(
    val title: String,
)

@Composable
fun HappyHourDetailScreenWrapper(
    modifier: Modifier = Modifier,
    happyHour: String,
    navController: NavHostController,
    viewModel: HappyHourDetailViewModel = koinViewModel()
) {
    HappyHourDetailScreen(
        modifier = modifier,
        happyHour = happyHour,
        onBackButtonClick = {
            navController.popBackStack()
        }
    )
}
@Composable
fun HappyHourDetailScreen(
    modifier: Modifier = Modifier,
    happyHour: String,
    onBackButtonClick: () -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        Text(text = happyHour.toString())
    }
}