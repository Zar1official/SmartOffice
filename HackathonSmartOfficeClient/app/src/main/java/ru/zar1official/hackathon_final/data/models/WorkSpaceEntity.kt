package ru.zar1official.hackathon_final.data.models

import kotlinx.serialization.Serializable

@Serializable
data class WorkSpaceEntity(
    val uId: Long,
    val microclimateType: Int,
    val lightControls: LightControlsEntity,
    val busyStatus: Boolean
)