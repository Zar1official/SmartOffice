package ru.zar1official.hackathon_final.domain.usecases.chill

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.domain.models.MassageMode
import ru.zar1official.hackathon_final.domain.models.PostModel
import ru.zar1official.hackathon_final.domain.repositories.Repository

class ChangeMassageModeUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(uId: Long, mode: MassageMode) = withContext(coroutineDispatcher) {
        val modeValue = when (mode) {
            is MassageMode.None -> 0
            is MassageMode.Vibration -> 1
            is MassageMode.AirCompression -> 2
        }

        kotlin.runCatching { repository.changeMassageMode(uId, modeValue) }.onFailure {
            return@withContext PostModel.Error
        }
        return@withContext PostModel.Success
    }
}