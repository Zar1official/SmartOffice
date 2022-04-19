package ru.zar1official.hackathon_final.domain.models

sealed class PostModel {
    object Success : PostModel()
    object Error : PostModel()
}
