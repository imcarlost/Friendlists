package com.hako.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.hako.base.R
import com.hako.base.extensions.inflate
import kotlinx.android.synthetic.main.like_button.view.*

private const val LIKE_MIN_FRAME = 0
private const val LIKE_MAX_FRAME = 28
private const val LIKE_ANIM_SPEED = 1f
private const val DISLIKE_MIN_FRAME = 29
private const val DISLIKE_MAX_FRAME = 70
private const val DISLIKE_ANIM_SPEED = 2f

class LikeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.like_button, true)
    }

    fun like() {
        like_button_animation_view.frame = LIKE_MAX_FRAME
    }

    fun dislike() {
        like_button_animation_view.frame = DISLIKE_MAX_FRAME
    }

    fun play() {
        if (like_button_animation_view.frame <= LIKE_MAX_FRAME){
            playDislike()
        } else {
            playLike()
        }
    }

    private fun playLike() {
        like_button_animation_view.setMinAndMaxFrame(LIKE_MIN_FRAME, LIKE_MAX_FRAME)
        like_button_animation_view.speed = LIKE_ANIM_SPEED
        like_button_animation_view.playAnimation()
    }

    private fun playDislike() {
        like_button_animation_view.setMinAndMaxFrame(DISLIKE_MIN_FRAME, DISLIKE_MAX_FRAME)
        like_button_animation_view.speed = DISLIKE_ANIM_SPEED
        like_button_animation_view.playAnimation()
    }
}