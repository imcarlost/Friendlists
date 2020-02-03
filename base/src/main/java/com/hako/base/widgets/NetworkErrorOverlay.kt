package com.hako.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.hako.base.R
import com.hako.base.extensions.inflate

class NetworkErrorOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.network_error_overlay, true)
    }
}