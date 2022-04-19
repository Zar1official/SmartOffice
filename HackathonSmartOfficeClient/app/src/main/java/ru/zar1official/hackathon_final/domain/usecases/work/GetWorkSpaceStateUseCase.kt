package ru.zar1official.hackathon_final.domain.usecases.work

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.domain.models.GetModel
import ru.zar1official.hackathon_final.domain.repositories.Repository

class GetWorkSpaceStateUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(uId: Long) = withContext(coroutineDispatcher) {
        val data = kotlin.runCatching { repository.getWorkSpaceState(uId) }.getOrNull()
        if (data != null) {
            return@withContext GetModel.Success(data)
        }
        return@withContext GetModel.Error
    }
}