package com.hako.base.extensions

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavHostFragment(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment

fun NavHostFragment.registerOnFragmentViewCreated(
    recursive: Boolean = true,
    listener: (currentFragment: Fragment) -> Unit
) {
    childFragmentManager
        .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                listener(f)
            }
        }, recursive)
}

fun buildNavigation(@IdRes id: Int, bundle: Bundle = Bundle()) = Pair(id, bundle)
