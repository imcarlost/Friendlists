package com.hako.base.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavHostFragment(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment

fun Fragment.findNavHostFragment(@IdRes id: Int) =
    childFragmentManager.findFragmentById(id) as NavHostFragment

fun Fragment.findNavController(@IdRes id: Int) =
    androidx.navigation.Navigation.findNavController(view?.findViewById(id) ?: viewNotFound(id, this))

private fun viewNotFound(@IdRes id: Int, fragment: Fragment): Nothing = throw IllegalStateException(
    "View ID $id at '${fragment::class.java.simpleName}' not found."
)