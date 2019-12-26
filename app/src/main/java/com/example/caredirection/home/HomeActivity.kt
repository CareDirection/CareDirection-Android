package com.example.caredirection.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.caredirection.ProductFragment
import com.example.caredirection.R
import com.example.caredirection.StudyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_activity)

        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation_view)as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_home  ->{
                val fragmentHome = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragmentHome).commit()
            }
            R.id.navigation_product -> {
                val fragmentProduct = ProductFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragmentProduct).commit()
            }
            R.id.navigation_study -> {
                val fragmentStudy = StudyFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragmentStudy).commit()
            }
        }
        return true
    }


}
