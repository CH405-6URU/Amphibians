package com.example.amphibians

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel

// Initiates the app and calls the home screen function
@Composable
fun AmphibianInformationApp(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val amphibianViewModel: AmphibianViewModel = viewModel()
            HomeScreen(amphibianUiState = amphibianViewModel.amphibianUiState)
        }
    }
}