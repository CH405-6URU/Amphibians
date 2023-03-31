package com.example.amphibians

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.request.ImageRequest
import com.example.amphibians.ui.theme.AmphibiansTheme


@Composable
fun HomeScreen(amphibianUiState: AmphibianUiState, modifier: Modifier = Modifier) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier)
        is AmphibianUiState.Error -> ErrorScreen(modifier)
        is AmphibianUiState.Success -> AmphibianColumnScreen(data = amphibianUiState.data)
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

@Composable
// Used https://abhichn.medium.com/how-to-create-a-list-in-jetpack-compose-using-lazycolumn-d8a6beb5bf65 for lazyColumn help
fun AmphibianColumnScreen(data: List<AmphibianData>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(data) {
            AmphibianDataCard(data = it)
        }
    }
}

@Composable
fun AmphibianDataCard(data: AmphibianData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = data.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = data.type, fontSize = 20.sp)
            Text(
                text = data.description,
                fontSize = 14.sp
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = "Amphibian image",
                Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AmphibiansTheme {
        val mockData = List(10) { AmphibianData("$it", "", "", "") }
        AmphibianColumnScreen(mockData)
    }
}
