package com.hako.friendlists.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.hako.base.extensions.observeNonNull
import com.hako.base.navigation.NavigationRouter
import com.hako.friendlists.BuildConfig
import com.hako.friendlists.R
import com.hako.friendlists.viewmodel.NavigationViewmodel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.main_fragment_container) }
    private val navRouter: NavigationRouter by inject()
    private val picasso: Picasso by inject()
    private val viewModel: NavigationViewmodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        setupPicasso()
    }

    private fun setupNavigation() {
        navController.setGraph(R.navigation.main_navigation)
        navRouter.setOnNavigationEvent {
            viewModel.onNavigationEvent(it)
        }
        viewModel.navigate.observeNonNull(this) { pair ->
            // Pair.first is a Navigation Id
            // Pair.second is a Bundle
            navController.navigate(pair.first, pair.second)
        }

        setupActionBarWithNavController(this, navController)
    }

    private fun setupPicasso() {
        // Show cache indicator on images just for debug builds
        picasso.setIndicatorsEnabled(BuildConfig.DEBUG)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}