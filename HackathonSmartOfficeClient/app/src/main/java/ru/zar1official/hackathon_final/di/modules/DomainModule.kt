package ru.zar1official.hackathon_final.di

import org.koin.dsl.module
import ru.zar1official.hackathon_final.domain.usecases.chill.ChangeMassageModeUseCase
import ru.zar1official.hackathon_final.domain.usecases.chill.GetChillSpaceStateUseCase
import ru.zar1official.hackathon_final.domain.usecases.chill.ReadCUIdUseCase
import ru.zar1official.hackathon_final.domain.usecases.work.*

val domainModule = module {
    factory<ChangeBrightUseCase> {
        ChangeBrightUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<ChangeBusyStatusUseCase> {
        ChangeBusyStatusUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<ChangeLightWarmUseCase> {
        ChangeLightWarmUseCase(
            repository = get(),
            coroutineDispatcher = get()
        )
    }

    factory<ChangeMassageModeUseCase> {
        ChangeMassageModeUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<ChangeMicroclimateTypeUseCase> {
        ChangeMicroclimateTypeUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<GetChillSpaceStateUseCase> {
        GetChillSpaceStateUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<GetWorkSpaceStateUseCase> {
        GetWorkSpaceStateUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<ReadWUIdUseCase> {
        ReadWUIdUseCase(repository = get(), coroutineDispatcher = get())
    }

    factory<ReadCUIdUseCase> {
        ReadCUIdUseCase(repository = get(), coroutineDispatcher = get())
    }
}