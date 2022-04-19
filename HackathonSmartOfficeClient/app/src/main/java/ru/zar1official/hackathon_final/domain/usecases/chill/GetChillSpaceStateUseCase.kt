package ru.zar1official.hackathon_final.domain.usecases.chill

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.domain.models.GetModel
import ru.zar1official.hackathon_final.domain.repositories.Repository

class GetChillSpaceStateUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(uId: Long) = withContext(coroutineDispatcher) {
        val data = kotlin.runCatching { repository.getChillSpaceState(uId) }.getOrNull()
        if (data != null) {
            return@withContext GetModel.Success(data)
        }
        return@withContext GetModel.Error
    }
}