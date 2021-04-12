package com.example.swapbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.swapbook.activities.ActivitiesFragment
import com.example.swapbook.home.HomeFragment
import com.example.swapbook.home.HomeFragmentDirections
import com.example.swapbook.insertion.InsertionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.ContextUtils.getActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.homeFragment

//        setupViews()
        bottom_navigation.setOnNavigationItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.searchBarFragment -> {
//                    val intent = Intent(this,HomeFragment::class.java)
//                    startActivity(intent)
                    true
                }
                R.id.insertionFragment -> {
                    val intent = Intent(this, InsertionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.chatFragment -> {
                    val intent = Intent(this,UsersListActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.activitiesFragment -> {
//                    val intent = Intent(this,HomeFragment::class.java)
//                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


    }


    fun setupViews()
    {
        var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottom_navigation, navHostFragment.navController)

        //var appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        var appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,
                R.id.searchBarFragment,
                R.id.chatFragment,
                R.id.activitiesFragment,
                R.id.insertionFragment))
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    fun showBottomNavigation()
    {
        bottom_navigation.visibility = View.VISIBLE
    }

    fun hideBottomNavigation()
    {
        bottom_navigation.visibility = View.GONE
    }



}