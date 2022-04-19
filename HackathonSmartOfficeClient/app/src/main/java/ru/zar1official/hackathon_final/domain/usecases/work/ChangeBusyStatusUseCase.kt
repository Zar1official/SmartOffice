package ru.zar1official.hackathon_final.domain.usecases.work

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.domain.models.PostModel
import ru.zar1official.hackathon_final.domain.repositories.Repository

class ChangeBusyStatusUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(uId: Long, busy: Boolean) =
        withContext(coroutineDispatcher) {
            kotlin.runCatching { repository.changeBusyStatus(uId, busy) }
                .onFailure {
                    return@withContext PostModel.Error
                }
            return@withContext PostModel.Success
        }
}