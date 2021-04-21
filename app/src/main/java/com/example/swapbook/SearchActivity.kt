package com.example.swapbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation.findNavController
import com.example.swapbook.network.Post
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)



        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.searchBarFragment

        Log.i("inizio","1")
        findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_searchFragment)
        Log.i("inizio","2")
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
                    val intent = Intent(this,SearchActivity::class.java)
                    startActivity(intent)
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


}