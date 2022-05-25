package com.example.myrecipes.data.repository.util

sealed class Resource<out T> {
    data class Success<out T>(
        val data: T,
        val code: Int? = null
    ) : Resource<T>()

    data class Error<out T>(
        val message: String? = null,
        val data: T?,
        val code: Int? = null
    ) : Resource<T>()

    data class Loading<out T>(
        val data: T?
    ) : Resource<T>()
}

sealed class Events {
    object LogOutEvent : Events()
    object TokenExpiredEvent: Events()
}