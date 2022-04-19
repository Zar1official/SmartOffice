package ru.zar1official.hackathon_final.domain.models

sealed class GetModel<out T> {
    data class Success<T>(val data: T) : GetModel<T>()
    object Error : GetModel<Nothing>()
}