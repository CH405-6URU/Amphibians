package com.example.amphibians

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.amphibians.ui.theme.AmphibiansTheme


@Composable
fun HomeScreen(amphibianUiState: AmphibianUiState, modifier: Modifier = Modifier) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier)
        is AmphibianUiState.Error -> ErrorScreen(modifier)
        is AmphibianUiState.Success -> FrogScreen(data = amphibianUiState.data)
    }
}
// Loading Screen
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Loading")
    }
}

// Error screen
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Error")
//        Button(onClick = retryAction) {
//            Text(stringResource(androidx.compose.foundation.layout.R.string.retry))
//        }
    }
}

// TODO Convert the list data to be displayed instead of raw string data

@Composable
fun FrogScreen(data: List<AmphibianData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Great Basin Spadefoot (Toad)", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            text = data.toString(),
            fontSize = 14.sp
        )
        Image(
            painter = painterResource(id = R.drawable.great_basin_spadefoot),
            contentDescription = "Toad",
            Modifier.padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AmphibiansTheme {
        val mockData = List(10) { AmphibianData("$it", "", "", "") }
        FrogScreen(mockData)
    }
}
