package com.hako.base.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false) =
    LayoutInflater
        .from(context)
        .inflate(layout, this, attachToRoot)
