package com.example.amphibians

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.ui.theme.AmphibianDataRepository
import com.example.amphibians.ui.theme.DefaultAmphibianDataRepository
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AmphibianUiState {
    data class Success(val data: List<AmphibianData>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}


class AmphibianViewModel(private val amphibianDataRepository: AmphibianDataRepository) : ViewModel() {

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)

    init{
        getAmphibianData()
    }

    private fun getAmphibianData() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianDataRepository.peepeepoopoo())
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianDataApplication)
                val amphibianDataRepository = application.container.amphibianDataRepository
                AmphibianViewModel(amphibianDataRepository = amphibianDataRepository)
            }
        }
    }
}
