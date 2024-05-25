package com.github.czinkem.thevr_happyhour_app.presentation.setupScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
@Serializable
object SetupScreen

@Composable
fun SetupScreenWrapper(
    modifier: Modifier = Modifier,
    viewModel: SetupScreenViewModel = koinViewModel()
) {
    SetupScreen(
        modifier = modifier,
        onOfflineModeClick = viewModel::setupOfflineMode,
        onOnlineModeClick = viewModel::setupSyncServer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupScreen(
    modifier: Modifier = Modifier,
    onOnlineModeClick: (syncServerUri: String) -> Unit,
    onOfflineModeClick: () -> Unit,
) {
    var syncServerUri by rememberSaveable {
        mutableStateOf("")
    }
    var isSyncingServerInfoTooltipShows by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(modifier = modifier) {

        AnimatedVisibility(isSyncingServerInfoTooltipShows) {
            Dialog(
                onDismissRequest = { isSyncingServerInfoTooltipShows = false }
            ) {

            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Setup Syncing Server")
            OutlinedIconButton(onClick = { isSyncingServerInfoTooltipShows = true }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "")
            }
            OutlinedTextField(
                value = syncServerUri,
                onValueChange = {
                    syncServerUri = it
                }
            )

            Button(onClick = {
                onOnlineModeClick(syncServerUri)
            }) {
                Text(text = "Setup With Server")
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "OR",
                    textAlign = TextAlign.Center,
                )
                Divider(
                    modifier = Modifier.weight(1f)
                )
            }

            Button(onClick = onOfflineModeClick) {
                Text(text = "Continue in Offline Mode")
            }
        }
    }
}