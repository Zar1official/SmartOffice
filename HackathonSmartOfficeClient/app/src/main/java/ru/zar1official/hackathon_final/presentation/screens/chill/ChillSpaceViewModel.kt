package ru.zar1official.hackathon_final.presentation.screens.chill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.zar1official.hackathon_final.domain.models.GetModel
import ru.zar1official.hackathon_final.domain.models.MassageMode
import ru.zar1official.hackathon_final.domain.models.PostModel
import ru.zar1official.hackathon_final.domain.usecases.chill.ChangeMassageModeUseCase
import ru.zar1official.hackathon_final.domain.usecases.chill.GetChillSpaceStateUseCase
import ru.zar1official.hackathon_final.domain.usecases.chill.ReadCUIdUseCase
import ru.zar1official.hackathon_final.presentation.screens.events.ScreenEvent

class ChillSpaceViewModel(
    private val getChillSpaceStateUseCase: GetChillSpaceStateUseCase,
    private val changeMassageModeUseCase: ChangeMassageModeUseCase,
    private val readCUIdUseCase: ReadCUIdUseCase
) :
    ViewModel() {
    val event = Channel<ScreenEvent>(0)

    private var cUID: Long = 2L

    private val _isLoaded = MutableLiveData<Boolean>()
    val isLoaded: LiveData<Boolean> = _isLoaded

    private val _massageMode = MutableLiveData<MassageMode>()
    val massageMode: LiveData<MassageMode> = _massageMode

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _currentSong = MutableLiveData<String>()
    val currentSong: LiveData<String> = _currentSong

    fun onGetChillSpaceState() {
        viewModelScope.launch {
            val id = readCUIdUseCase.invoke()
            cUID = id
            when (val state = getChillSpaceStateUseCase(cUID)) {
                is GetModel.Error -> showLoadingError()
                is GetModel.Success -> {
                    val data = state.data
                    _massageMode.value = when (data.massageMode) {
                        0 -> MassageMode.None
                        1 -> MassageMode.Vibration
                        else -> MassageMode.AirCompression
                    }
                    _isLoaded.value = true
                }
            }
        }
    }

    /*Not yet implemented*/
    fun onNextMedia() {

    }

    /*Not yet implemented*/
    fun onPreviousMedia() {

    }

    /*Not yet implemented*/
    fun onLoadSong() {

    }

    /*Not yet implemented*/
    fun onPlayMedia() {
        val value = _isPlaying.value ?: false
        _currentSong.value = "Playing something..."
        _isPlaying.value = !value
    }

    fun onChangeMassageMode(mode: MassageMode) {
        viewModelScope.launch {
            when (changeMassageModeUseCase(cUID, mode)) {
                is PostModel.Error -> showError()
                is PostModel.Success -> _massageMode.value = mode
            }
        }
    }

    private fun showError() {
        event.trySend(ScreenEvent.Error)
    }

    private fun showLoadingError() {
        event.trySend(ScreenEvent.LoadingError)
    }

}