package ru.zar1official.hackathon_final.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ChillSpaceEntity(
    val uId: Long,
    val massageMode: Int
)