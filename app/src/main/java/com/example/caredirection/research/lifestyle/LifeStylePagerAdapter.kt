package com.example.caredirection.research.lifestyle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class LifeStylePagerAdapter (fm:FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
         return when(position){
            0 -> LifeStyleAlcoholFragment()
            1 -> LifeStyleActivityFragment()
            2 -> LifeStyleVegetableFragment()
             3 -> LifeStyleExerciseFragment()
            else -> LifeStyleActivityFragment()
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }
}