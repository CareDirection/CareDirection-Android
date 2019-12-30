package com.example.caredirection.research.lifestyle


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.caredirection.R
import com.example.caredirection.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_life_style_activity.*

/**
 * A simple [Fragment] subclass.
 */
class LifeStyleActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_style_activity, container, false)

        makeController()
    }

    private fun makeController(){
        btn_activity_next.setOnClickListener {
         /*   val home_intent = Intent(this,HomeActivity::class.java)
            home_intent.putExtra("username","테스트용")

            startActivity(home_intent)*/
        }
    }

}
