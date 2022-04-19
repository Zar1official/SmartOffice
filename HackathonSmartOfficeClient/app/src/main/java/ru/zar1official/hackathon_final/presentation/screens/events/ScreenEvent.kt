package ru.zar1official.hackathon_final.presentation.screens.events

sealed class ScreenEvent {
    object LoadingError : ScreenEvent()
    object Error : ScreenEvent()
}