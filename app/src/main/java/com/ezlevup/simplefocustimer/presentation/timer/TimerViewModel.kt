package com.ezlevup.simplefocustimer.presentation.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlevup.simplefocustimer.domain.model.FocusSession
import com.ezlevup.simplefocustimer.domain.usecase.SaveFocusSessionUseCase
import com.ezlevup.simplefocustimer.domain.usecase.StartTimerUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TimerUiState(
        val currentTime: Long = 25 * 60,
        val totalTime: Long = 25 * 60,
        val isTimerRunning: Boolean = false,
        val progress: Float = 1f
)

class TimerViewModel(
        private val startTimerUseCase: StartTimerUseCase,
        private val saveFocusSessionUseCase: SaveFocusSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TimerUiState())
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer() {
        if (_uiState.value.isTimerRunning) return

        _uiState.update { it.copy(isTimerRunning = true) }

        timerJob =
                startTimerUseCase(_uiState.value.currentTime)
                        .onEach { remainingTime ->
                            _uiState.update {
                                it.copy(
                                        currentTime = remainingTime,
                                        progress = remainingTime.toFloat() / it.totalTime
                                )
                            }
                        }
                        .onCompletion { cause ->
                            if (cause == null && _uiState.value.currentTime == 0L) {
                                onTimerFinished()
                            }
                        }
                        .launchIn(viewModelScope)
    }

    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.update { it.copy(isTimerRunning = false) }
    }

    fun stopTimer() {
        timerJob?.cancel()
        _uiState.update {
            it.copy(currentTime = it.totalTime, isTimerRunning = false, progress = 1f)
        }
    }

    private fun onTimerFinished() {
        _uiState.update { it.copy(isTimerRunning = false) }
        viewModelScope.launch {
            saveFocusSessionUseCase(
                    FocusSession(
                            startTime =
                                    System.currentTimeMillis() - (_uiState.value.totalTime * 1000),
                            durationSeconds = _uiState.value.totalTime
                    )
            )
            // Reset timer after saving
            _uiState.update { it.copy(currentTime = it.totalTime, progress = 1f) }
        }
    }
}
