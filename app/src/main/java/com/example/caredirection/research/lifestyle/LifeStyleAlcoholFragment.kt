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
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_life_style.*
import kotlinx.android.synthetic.main.activity_life_style.view.*
import kotlinx.android.synthetic.main.activity_research_disease.*


/**
 * A simple [Fragment] subclass.
 */
class LifeStyleAlcoholFragment : Fragment() {

    private lateinit var lifestyle1 : List<RadioButton>
    private lateinit var keeper: ResearchKeeper

    private var check1: Boolean = false
    private var check2: Boolean = false
    private lateinit var Lifeview:View

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
        Lifeview =inflater.inflate(R.layout.fragment_life_style_alcohol, container, false)
        return Lifeview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val btn : Button = activity!!.findViewById(R.id.btn_life_next)

        txt_alcohol_title_1.text = keeper.name + "님은 담배를 피시나요?"
        txt_alcohol_title_2.text = keeper.name + "님은 술을 주 2회 이상" + "\n" + "드시나요?"

        setColorInPartitialAlcohol()
        setColorInPartitialSmoke()

        if(keeper.cigarette?:-1 == -1){
            rg_alcohol_1.clearCheck()

        }else{
            rg_alcohol_1.check(keeper.cigarette!!)
            check1 = true
        }
        if(keeper.alcohol?:-1 == -1){
            rg_alcohol_2.clearCheck()
        }else{
            rg_alcohol_2.check(keeper.alcohol!!)
            check2 = true
        }

//        if(check1==true&& check2==true){
//            btn.isEnabled = true
//            btn.setTextColor(resources.getColor(R.color.colorPrimary))
//        }
//        else{
//            btn.isEnabled = false
//            btn.setTextColor(resources.getColor(R.color.colorWhite))
//        }

        rg_alcohol_1.setOnCheckedChangeListener { radioGroup, i ->
            keeper.cigarette = i
            radioGroup.checkedRadioButtonId.toString().logDebug()
            check1 = true
            if(check1==true&& check2==true){
                btn.isEnabled = true
                btn.setTextColor(resources.getColor(R.color.colorPrimary))
            }
        }

        rg_alcohol_2.setOnCheckedChangeListener { radioGroup, i ->
            keeper.alcohol = radioGroup.checkedRadioButtonId
            Log.v("YGYG", radioGroup.checkedRadioButtonId.toString())
            check2 = true
            if(check1==true&& check2==true){
                btn.isEnabled = true
                btn.setTextColor(resources.getColor(R.color.colorPrimary))
            }
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