package com.github.czinkem.thevr_happyhour_app.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.czinkem.thevr_happyhour_app.domain.state.SearchType
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourSerialNumberValidator

@Composable
fun HappyHourSearchDialog(
    modifier: Modifier = Modifier,
    lastHappyHourSerialNumber: Int,
    onSearchClick: (type: SearchType, searchedValue: String) -> Unit,
    onDismiss: () -> Unit
) {
    var searchByText by rememberSaveable {
        mutableStateOf("")
    }

    var searchByNumber by rememberSaveable {
        mutableStateOf("")
    }
    val isSearchByNumberValid by remember {
        derivedStateOf {
            HappyHourSerialNumberValidator.isValid(
                serialNumberString = searchByNumber,
                min = 1,
                max = lastHappyHourSerialNumber
            )
        }
    }

    var searchType: SearchType? by rememberSaveable {
        mutableStateOf(null)
    }

    val isSearchButtonEnabled by remember {
         derivedStateOf {
             searchType != null
                     && (searchType == SearchType.NUMBER && isSearchByNumberValid)
         }
    }


    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Search",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "by text",
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                )
                OutlinedTextField(
                    modifier = Modifier.onFocusChanged { focusState ->
                        if(focusState.isFocused) {
                            searchType = SearchType.TEXT
                        }
                    },
                    value = searchByText,
                    onValueChange = {
                        searchByText = it
                    },
                    shape = RoundedCornerShape(8.dp)

                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "by episode number",
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start,
                )
                OutlinedTextField(
                    modifier = Modifier.onFocusChanged { focusState ->
                        if(focusState.isFocused) {
                            searchType = SearchType.NUMBER
                        }
                    },
                    value = searchByNumber,
                    onValueChange = {
                        searchByNumber = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = !isSearchByNumberValid,
                    singleLine = true,
                )
                AnimatedVisibility(visible = !isSearchByNumberValid) {
                    Text(text = "Must be in: 1 - $lastHappyHourSerialNumber")
                }
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "by date",
//                    fontStyle = FontStyle.Italic,
//                )
//                OutlinedTextField(value = "", onValueChange = {})
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isSearchButtonEnabled,
                    onClick = {
                        onSearchClick(
                            searchType!!,
                            when(searchType!!) {
                                SearchType.TEXT -> searchByText
                                SearchType.NUMBER -> searchByNumber
                                SearchType.DATE -> TODO()
                            }
                        )
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Text(text = "Search")
                }

                TextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        text = "Cancel",
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }
    }
}