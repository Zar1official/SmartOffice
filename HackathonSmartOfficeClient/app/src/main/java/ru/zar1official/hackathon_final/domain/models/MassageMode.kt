package ru.zar1official.hackathon_final.domain.models

import androidx.annotation.StringRes
import ru.zar1official.hackathon_final.R

sealed class MassageMode(@StringRes val name: Int) {
    object None : MassageMode(name = R.string.massage_none)
    object Vibration : MassageMode(name = R.string.massage_vibration)
    object AirCompression : MassageMode(name = R.string.massage_air_compression)
}