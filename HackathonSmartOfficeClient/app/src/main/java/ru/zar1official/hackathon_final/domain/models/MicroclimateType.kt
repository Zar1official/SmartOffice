package ru.zar1official.hackathon_final.domain.models

import androidx.annotation.DrawableRes
import ru.zar1official.hackathon_final.R

sealed class MicroclimateType(@DrawableRes val icon: Int) {
    object Comfortable : MicroclimateType(icon = R.drawable.ic_normal_temperature)
    object Heating : MicroclimateType(icon = R.drawable.ic_heating)
    object Cooling : MicroclimateType(icon = R.drawable.ic_cooling)
}
