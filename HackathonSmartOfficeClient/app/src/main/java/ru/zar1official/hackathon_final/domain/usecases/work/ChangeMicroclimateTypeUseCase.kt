package ru.zar1official.hackathon_final.domain.usecases.work

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.domain.models.MicroclimateType
import ru.zar1official.hackathon_final.domain.models.PostModel
import ru.zar1official.hackathon_final.domain.repositories.Repository

class ChangeMicroclimateTypeUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(uId: Long, type: MicroclimateType) =
        withContext(coroutineDispatcher) {
            val microClimateValue = when (type) {
                is MicroclimateType.Comfortable -> 0
                is MicroclimateType.Cooling -> 1
                is MicroclimateType.Heating -> 2
            }

            kotlin.runCatching { repository.changeMicroclimate(uId, microClimateValue) }.onFailure {
                return@withContext PostModel.Error
            }
            return@withContext PostModel.Success
        }
}