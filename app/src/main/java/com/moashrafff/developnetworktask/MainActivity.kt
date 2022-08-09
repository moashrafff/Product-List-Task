package com.moashrafff.developnetworktask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.landingFragment,
            R.id.loginFragment,
            R.id.registerFragment

        ))
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController,appBarConfiguration)
//        navController = findNavController(R.id.nav_host_fragment_container)
//        NavigationUI.setupActionBarWithNavController(this, navController)
//        navController.addOnDestinationChangedListener { controller, _, _ ->
//            val shouldShowUpButton = controller.previousBackStackEntry != null
//            supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowUpButton)
        }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    }

