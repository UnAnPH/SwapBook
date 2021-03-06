package com.example.swapbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.swapbook.chat.ChatFragment
import com.example.swapbook.home.HomeFragment
import com.example.swapbook.home.HomeFragmentDirections
import com.example.swapbook.insertion.InsertionFragment
import com.example.swapbook.searchbar.SearchBarFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.ContextUtils.getActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()

    }

//        val homeFragment= HomeFragment()
//        val searchBarFragment= SearchBarFragment()
//        val addFragment= InsertionFragment()
//        val chatFragment= ChatFragment()
//        val activitiesFragment= ActivitiesFragment()
//
//        makeCurrentFragment(homeFragment)
//
//        bottom_navigation.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.home-> makeCurrentFragment(homeFragment)
//                R.id.search_bar-> makeCurrentFragment(searchBarFragment)
//                R.id.add-> makeCurrentFragment(addFragment)
//                R.id.chat-> makeCurrentFragment(chatFragment)
//                R.id.activities-> makeCurrentFragment(activitiesFragment)
//            }
//            true
//            }
//
//        }
//
//    fun makeCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//        replace(R.id.fl_wrapper, fragment)
//        commit()
//    }
//



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



}