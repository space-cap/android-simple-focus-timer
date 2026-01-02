package com.ezlevup.simplefocustimer.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlevup.simplefocustimer.domain.model.FocusSession
import com.ezlevup.simplefocustimer.domain.usecase.GetFocusHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class HistoryUiState(val sessions: List<FocusSession> = emptyList())

class HistoryViewModel(private val getFocusHistoryUseCase: GetFocusHistoryUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        getFocusHistoryUseCase()
                .onEach { sessions -> _uiState.value = HistoryUiState(sessions = sessions) }
                .launchIn(viewModelScope)
    }
}
