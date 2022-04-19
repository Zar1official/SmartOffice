package ru.zar1official.hackathon_final.domain.usecases.work

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.domain.models.PostModel
import ru.zar1official.hackathon_final.domain.repositories.Repository

class ChangeBrightUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(uId: Long, bright: Int) =
        withContext(coroutineDispatcher) {
            kotlin.runCatching { repository.changeLightBright(uId, bright) }
                .onFailure {
                    return@withContext PostModel.Error
                }
            return@withContext PostModel.Success
        }
}