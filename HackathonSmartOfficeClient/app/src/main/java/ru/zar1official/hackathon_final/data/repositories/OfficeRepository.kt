package ru.zar1official.hackathon_final.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import ru.zar1official.hackathon_final.data.models.ChillSpaceEntity
import ru.zar1official.hackathon_final.data.models.WorkSpaceEntity
import ru.zar1official.hackathon_final.data.network.Service
import ru.zar1official.hackathon_final.domain.repositories.Repository

class OfficeRepository(
    private val service: Service,
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) : Repository {
    override suspend fun changeBusyStatus(uId: Long, busy: Boolean) {
        service.changeBusyStatus(uId, busy)
    }

    override suspend fun changeLightWarm(uId: Long, warm: Int) {
        service.changeLightWarm(uId, warm)
    }

    override suspend fun changeLightBright(uId: Long, bright: Int) {
        service.changeLightBright(uId, bright)
    }

    override suspend fun changeMicroclimate(uId: Long, microclimateType: Int) {
        service.changeMicroclimate(uId, microclimateType)
    }

    override suspend fun getWorkSpaceState(uId: Long): WorkSpaceEntity {
        return service.getWorkSpaceState(uId)
    }

    override suspend fun changeMassageMode(uId: Long, mode: Int) {
        service.changeMassageMode(uId, mode)
    }

    override suspend fun getChillSpaceState(uId: Long): ChillSpaceEntity {
        return service.getChillSpaceState(uId)
    }

    override suspend fun readLongFromPrefs(key: String): Long? {
        val prefs = dataStore.data.first()
        val dataStoreKey = longPreferencesKey(name = key)
        return prefs[dataStoreKey]
    }

    override suspend fun saveLongInPrefs(key: String, long: Long) {
        dataStore.edit { settings ->
            val dataStoreKey = longPreferencesKey(name = key)
            settings[dataStoreKey] = long
        }
    }
}