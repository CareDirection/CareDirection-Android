package com.example.caredirection.research.lifestyle


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_gender.*
import kotlinx.android.synthetic.main.fragment_life_style_alcohol.*
import android.graphics.Typeface
import android.text.style.StyleSpan
import android.widget.TextView





/**
 * A simple [Fragment] subclass.
 */
class LifeStyleAlcoholFragment : Fragment() {

    private lateinit var keeper : ResearchKeeper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //keeper = ResearchKeeper(this)
        //setColorInPartitial()
        return inflater.inflate(R.layout.fragment_life_style_alcohol, container, false)
    }

}
