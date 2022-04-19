package ru.zar1official.hackathon_final.domain.usecases.chill

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.hackathon_final.data.storage.StorageConstants
import ru.zar1official.hackathon_final.domain.repositories.Repository

class ReadCUIdUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun invoke(): Long = withContext(coroutineDispatcher) {
        var uId = repository.readLongFromPrefs(StorageConstants.storageSettingsCuId)
        if (uId == null) {
            uId = generateCuId()
            repository.saveLongInPrefs(key = StorageConstants.storageSettingsCuId, long = uId)
        }
        return@withContext uId
    }

    private fun generateCuId(): Long = ("2" + (0..Int.MAX_VALUE).random().toString()).toLong()
}