package com.hako.base.extensions

import android.os.Bundle
import androidx.annotation.IdRes

fun buildNavigation(@IdRes id: Int, bundle: Bundle = Bundle()) = Pair(id, bundle)
