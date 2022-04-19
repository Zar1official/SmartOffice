package ru.zar1official.hackathon_final.presentation.screens.work

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.zar1official.hackathon_final.domain.models.GetModel
import ru.zar1official.hackathon_final.domain.models.MicroclimateType
import ru.zar1official.hackathon_final.domain.models.PostModel
import ru.zar1official.hackathon_final.domain.usecases.work.*
import ru.zar1official.hackathon_final.presentation.screens.events.ScreenEvent

class WorkSpaceViewModel(
    private val getWorkSpaceStateUseCase: GetWorkSpaceStateUseCase,
    private val changeBrightUseCase: ChangeBrightUseCase,
    private val changeBusyStatusUseCase: ChangeBusyStatusUseCase,
    private val changeMicroclimateTypeUseCase: ChangeMicroclimateTypeUseCase,
    private val changeLightWarmUseCase: ChangeLightWarmUseCase,
    private val readWUIdUseCase: ReadWUIdUseCase
) : ViewModel() {
    val event = Channel<ScreenEvent>(0)

    private var wUID: Long = 1L

    private val _isLoaded = MutableLiveData<Boolean>()
    val isLoaded: LiveData<Boolean> = _isLoaded

    private val _microclimateType = MutableLiveData<MicroclimateType>()
    val microclimateType: LiveData<MicroclimateType> = _microclimateType

    private val _busyStatus = MutableLiveData<Boolean>()
    val busyStatus: LiveData<Boolean> = _busyStatus

    private val _bright = MutableLiveData<Int>()
    val bright: LiveData<Int> = _bright

    private val _warm = MutableLiveData<Int>()
    val warm: LiveData<Int> = _warm

    fun onChangeMicroclimateType(type: MicroclimateType) {
        if (microclimateType.value != type) {
            viewModelScope.launch {
                when (changeMicroclimateTypeUseCase(wUID, type)) {
                    is PostModel.Error -> showError()
                    is PostModel.Success -> _microclimateType.value = type
                }
            }
        }
    }

    fun onChangeBusyStatus() {
        val next = !(busyStatus.value ?: false)
        viewModelScope.launch {
            when (changeBusyStatusUseCase(wUID, next)) {
                is PostModel.Error -> showError()
                is PostModel.Success -> _busyStatus.value = next
            }
        }
    }

    fun onGetWorkSpaceState() {
        if (isLoaded.value != true) {
            viewModelScope.launch {
                val id = readWUIdUseCase.invoke()
                wUID = id
                when (val state = getWorkSpaceStateUseCase(wUID)) {
                    is GetModel.Error -> showLoadingError()
                    is GetModel.Success -> {
                        val data = state.data
                        _microclimateType.value = when (data.microclimateType) {
                            0 -> MicroclimateType.Comfortable
                            1 -> MicroclimateType.Cooling
                            else -> MicroclimateType.Heating
                        }
                        _busyStatus.value = data.busyStatus
                        _isLoaded.value = true
                    }
                }
            }
        }
    }

    fun onSaveBright(value: Int) {
        viewModelScope.launch {
            if (changeBrightUseCase(wUID, value) is PostModel.Error) {
                showError()
            }
        }
    }

    fun onChangeBright(bright: Int) {
        _bright.value = bright
    }

    fun onSaveWarm(value: Int) {
        viewModelScope.launch {
            if (changeLightWarmUseCase(wUID, value) is PostModel.Error) {
                showError()
            }
        }
    }

    fun onChangeWarm(warm: Int) {
        _warm.value = warm
    }

    private fun showError() {
        event.trySend(ScreenEvent.Error)
    }

    private fun showLoadingError() {
        event.trySend(ScreenEvent.LoadingError)
    }
}