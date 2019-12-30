package com.example.caredirection.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.caredirection.R
import com.example.caredirection.study.StudyFragment
import com.example.caredirection.product.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val fragmentHome = HomeFragment()
    private val fragmentProduct = SearchFragment()
    private val fragmentStudy = StudyFragment()
    private val fragments = mapOf(
        R.id.navigation_home to fragmentHome,
        R.id.navigation_product to fragmentProduct,
        R.id.navigation_study to fragmentStudy
    )
    private lateinit var active: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_activity)

        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation_view)as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        fragments.forEach { (key, frag) ->
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, frag, key.toString()).hide(frag)
                .commit()
        }
        supportFragmentManager.beginTransaction().show(fragmentHome).commit()
        active = fragmentHome
    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            in fragments.keys -> {
                fragments[p0.itemId]?.let {
                    supportFragmentManager.beginTransaction().hide(active).show(it).commit()
                    active = it
                }
            }
            else -> return false
        }
        return true
    }
}

