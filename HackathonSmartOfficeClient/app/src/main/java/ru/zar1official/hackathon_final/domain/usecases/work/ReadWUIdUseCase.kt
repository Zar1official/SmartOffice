package ru.zar1official.hackathon_final.domain.usecases.work

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.data.storage.StorageConstants
import ru.zar1official.hackathon_final.domain.repositories.Repository

class ReadWUIdUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun invoke(): Long = withContext(coroutineDispatcher) {
        var uId = repository.readLongFromPrefs(StorageConstants.storageSettingsWuId)
        if (uId == null) {
            uId = generateWuId()
            repository.saveLongInPrefs(key = StorageConstants.storageSettingsWuId, long = uId)
        }
        return@withContext uId
    }

    private fun generateWuId(): Long = ("1" + (0..Int.MAX_VALUE).random().toString()).toLong()
}