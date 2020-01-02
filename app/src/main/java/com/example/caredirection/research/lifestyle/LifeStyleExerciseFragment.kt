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
import android.widget.Button

import com.example.caredirection.R
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.fragment_life_style_exercise.*

/**
 * A simple [Fragment] subclass.
 */
class LifeStyleExerciseFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_life_style_exercise, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val btn : Button = activity!!.findViewById(R.id.btn_life_next)

        txt_temp_title.text = keeper.name + "님은"+"\n"+"땀이 날 정도의 운동을"+"\n"+"일주일에 얼마나 하시나요?"

        setColorInPartitial()

        if(keeper.temp?:-1 == -1){
            rg_temp_1.clearCheck()
            btn.isEnabled = false
            btn.setTextColor(resources.getColor(R.color.colorWhite))
        }else{
            rg_temp_1.check(keeper.temp!!)
            btn.isEnabled = true
            btn.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        rg_temp_1.setOnCheckedChangeListener { radioGroup, i ->
            keeper.temp = rg_temp_1.checkedRadioButtonId
            Log.v("YGYG", rg_temp_1.checkedRadioButtonId.toString())
            btn.isEnabled = true
            btn.setTextColor(resources.getColor(R.color.colorPrimary))
        }

    }

    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_temp_title?.text.toString())
        val start : Int = keeper.name!!.length + 12


        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            start,
            start + 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_temp_title?.text = builder
    }
}
