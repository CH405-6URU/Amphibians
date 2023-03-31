package com.example.amphibians

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AmphibianUiState {
    data class Success(val data: List<AmphibianData>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}


class AmphibianViewModel() : ViewModel() {
    // I think I need 3 variables for each part of the card but I do not see why this could not be
    // replaced with 1 list later.
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
//    var amphibianTitle: String by mutableStateOf("")
//    var amphibianType: String by mutableStateOf("")
//    var amphibianDescription: String by mutableStateOf("")
//    var amphibianImage: String by mutableStateOf("")


    init{
        getAmphibianData()
    }

    private fun getAmphibianData() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                val result = AmphibianApi.retrofitService.getAmphibians()
                AmphibianUiState.Success(result)
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }
}
