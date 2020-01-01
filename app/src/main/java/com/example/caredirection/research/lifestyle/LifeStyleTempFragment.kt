package com.example.caredirection.research.lifestyle


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.caredirection.R
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.fragment_life_style_temp.*

/**
 * A simple [Fragment] subclass.
 */
class LifeStyleTempFragment : Fragment() {

    private lateinit var keeper: ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keeper = ResearchKeeper(context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_style_temp, container, false)
    }

    override fun onStart() {
        super.onStart()
        txt_temp_title.text = keeper.name + "님은"+"\n"+"일주일에 몇 회 이상"+"\n"+"스크럼을 하시나요?"

        setColorInPartitial()

        if(keeper.temp?:-1 == -1){
            rg_temp_1.clearCheck()
        }else{
            rg_temp_1.check(keeper.temp!!)
        }
        rg_temp_1.setOnCheckedChangeListener { radioGroup, i ->
            keeper.temp = rg_temp_1.checkedRadioButtonId
            Log.v("YGYG", rg_temp_1.checkedRadioButtonId.toString())

        }

    }

    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_temp_title?.text.toString())
        val start : Int = keeper.name!!.length + 15


        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            start,
            start + 3,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_temp_title?.text = builder
    }
}
