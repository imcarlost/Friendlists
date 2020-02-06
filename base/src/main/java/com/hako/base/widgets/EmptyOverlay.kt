package com.hako.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.hako.base.R
import com.hako.base.extensions.inflate
import kotlinx.android.synthetic.main.empty_overlay.view.*

class EmptyOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.empty_overlay, true)
    }

    fun setLabel(message: String) {
        empty_overlay_label.text = message
    }
}