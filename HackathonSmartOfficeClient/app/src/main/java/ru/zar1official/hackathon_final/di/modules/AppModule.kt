package ru.zar1official.hackathon_final.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.zar1official.hackathon_final.presentation.screens.chill.ChillSpaceViewModel
import ru.zar1official.hackathon_final.presentation.screens.work.WorkSpaceViewModel

val appModule = module {
    factory<CoroutineDispatcher> { Dispatchers.IO }

    viewModel<ChillSpaceViewModel> {
        ChillSpaceViewModel(
            getChillSpaceStateUseCase = get(),
            changeMassageModeUseCase = get(),
            readCUIdUseCase = get()
        )
    }

    viewModel<WorkSpaceViewModel> {
        WorkSpaceViewModel(
            getWorkSpaceStateUseCase = get(),
            changeBrightUseCase = get(),
            changeBusyStatusUseCase = get(),
            changeLightWarmUseCase = get(),
            changeMicroclimateTypeUseCase = get(),
            readWUIdUseCase = get()
        )
    }
}