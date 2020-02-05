package com.hako.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, func: (T) -> Unit) {
    observe(owner, Observer {
        it?.let {
            func(it)
        }
    })
}
