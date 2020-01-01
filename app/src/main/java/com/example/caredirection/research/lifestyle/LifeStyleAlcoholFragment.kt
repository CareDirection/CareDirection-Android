package com.example.caredirection.research.lifestyle


import android.content.Intent
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
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_life_style.*
import kotlinx.android.synthetic.main.activity_research_disease.*


/**
 * A simple [Fragment] subclass.
 */
class LifeStyleAlcoholFragment : Fragment() {

    private lateinit var lifestyle1 : List<RadioButton>
    private lateinit var keeper: ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keeper = ResearchKeeper(context!!)

        lifestyle1 = listOf(
            btn_alcohol_1,btn_alcohol_2,btn_alcohol_3,btn_alcohol_4
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_life_style_alcohol, container, false)

    }

    override fun onStart() {
        super.onStart()
        txt_alcohol_title_1.text = keeper.name + "님은 담배를 피시나요?"
        txt_alcohol_title_2.text = keeper.name + "님은 술을 주 2회 이상" + "\n" + "드시나요?"

        setColorInPartitialAlcohol()
        setColorInPartitialSmoke()

        if(keeper.alcohol?:-1 == -1){
            rg_alcohol_1.clearCheck()
        }else{
            rg_alcohol_1.check(keeper.alcohol!!)
        }
        rg_alcohol_1.setOnCheckedChangeListener { radioGroup, i ->
            keeper.alcohol = rg_alcohol_1.checkedRadioButtonId
            Log.v("YGYG", rg_alcohol_1.checkedRadioButtonId.toString())
            //checkSelectButton()
        }

        if(keeper.cigarette?:-1 == -1){
            rg_alcohol_2.clearCheck()
        }else{
            rg_alcohol_2.check(keeper.cigarette!!)
        }
        rg_alcohol_2.setOnCheckedChangeListener { radioGroup, i ->
            keeper.cigarette = rg_alcohol_2.checkedRadioButtonId
            Log.v("YGYG", rg_alcohol_2.checkedRadioButtonId.toString())
            //checkSelectButton()
        }

    }

    private fun checkSelectButton(){
        if(lifestyle1.any { it.isChecked }){
            btn_life_next.isEnabled = true
        }
        else{
            btn_life_next.isEnabled = false
        }
    }

    private fun setColorInPartitialAlcohol(){
        val builder = SpannableStringBuilder(txt_alcohol_title_2?.text.toString())
        val start : Int = keeper.name!!.length + 3


        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            start,
            start + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_alcohol_title_2?.text = builder
    }

    private fun setColorInPartitialSmoke(){
        val builder = SpannableStringBuilder(txt_alcohol_title_1?.text.toString())
        val start : Int = keeper.name!!.length + 3


        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            start,
            start + 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_alcohol_title_1?.text = builder
    }


}