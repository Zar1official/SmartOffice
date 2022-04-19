package ru.zar1official.hackathon_final.presentation.screens.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.zar1official.hackathon_final.R
import ru.zar1official.hackathon_final.presentation.PresentationConstants

sealed class BottomBarScreen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int
) {
    object WorkSpace :
        BottomBarScreen(
            route = PresentationConstants.workSpaceScreenRoute,
            title = R.string.work_space_title,
            icon = R.drawable.ic_work_space,
            contentDescription = R.string.work_space_title
        )

    object ChillSpace :
        BottomBarScreen(
            route = PresentationConstants.chillSpaceScreenRoute,
            title = R.string.chill_space_title,
            icon = R.drawable.ic_chill_space,
            contentDescription = R.string.chill_space_title
        )
}
