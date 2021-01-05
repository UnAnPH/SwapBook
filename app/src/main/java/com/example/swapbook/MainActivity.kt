package com.example.swapbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.swapbook.activities.ActivitiesFragment
import com.example.swapbook.chat.ChatFragment
import com.example.swapbook.home.HomeFragment
import com.example.swapbook.home.HomeFragmentDirections
import com.example.swapbook.insertion.InsertionFragment
import com.example.swapbook.searchbar.SearchBarFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment= HomeFragment()
        val searchBarFragment= SearchBarFragment()
        val addFragment= InsertionFragment()
        val chatFragment= ChatFragment()
        val activitiesFragment= ActivitiesFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> makeCurrentFragment(homeFragment)
                R.id.search_bar-> makeCurrentFragment(searchBarFragment)
                R.id.add-> makeCurrentFragment(addFragment)
                R.id.chat-> makeCurrentFragment(chatFragment)
                R.id.activities-> makeCurrentFragment(activitiesFragment)
            }
            true
            }
        }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }
    }


}