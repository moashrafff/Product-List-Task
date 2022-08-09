package com.moashrafff.developnetworktask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.moashrafff.developnetworktask.data.source.UserPreferences
import com.moashrafff.developnetworktask.views.pages.a_Login.UserViewModel
import com.moashrafff.developnetworktask.views.pages.b_SignUp.RegisterFragmentDirections
import com.moashrafff.developnetworktask.views.pages.c_Landing.LandingFragment
import com.moashrafff.developnetworktask.views.pages.c_Landing.LandingFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private val userViewModel: UserViewModel by  viewModels()
    @Inject
    lateinit  var userPref: UserPreferences

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
        }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item.itemId == R.id.logout){
             userViewModel.updateStatus(userPref.getUserMail(),"false")
             navController.navigate(R.id.loginFragment)
             navController.popBackStack(R.id.loginFragment, false)
             return true
        }else {
             return super.onOptionsItemSelected(item)
        }

    }

    }

