package ru.zar1official.hackathon_final.data.network

import ru.zar1official.hackathon_final.data.models.ChillSpaceEntity
import ru.zar1official.hackathon_final.data.models.WorkSpaceEntity

interface Service {
    suspend fun changeBusyStatus(uId: Long, busy: Boolean)
    suspend fun changeLightWarm(uId: Long, warm: Int)
    suspend fun changeLightBright(uId: Long, bright: Int)
    suspend fun changeMicroclimate(uId: Long, microclimateType: Int)
    suspend fun getWorkSpaceState(uId: Long): WorkSpaceEntity

    suspend fun changeMassageMode(uId: Long, mode: Int)
    suspend fun getChillSpaceState(uId: Long): ChillSpaceEntity
}