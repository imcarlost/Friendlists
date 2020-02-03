package com.hako.base.domain

interface UseCase <T> {
    fun execute(onSuccess: (List<T>) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit)
}