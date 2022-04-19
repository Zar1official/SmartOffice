package ru.zar1official.hackathon_final.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LightControlsEntity(
    val brightControl: Int,
    val warmControl: Int
)