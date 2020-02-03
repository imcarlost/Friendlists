package com.hako.friendlists.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.hako.friendlists.R

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.main_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        navController.setGraph(R.navigation.main_navigation)
    }

}