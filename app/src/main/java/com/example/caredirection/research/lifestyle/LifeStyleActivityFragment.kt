package com.example.caredirection.research.lifestyle


import android.content.Intent
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
import android.widget.Toast

import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.home.HomeActivity
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.fragment_life_style_activity.*
import kotlinx.android.synthetic.main.fragment_life_style_alcohol.*

/**
 * A simple [Fragment] subclass.
 */
class LifeStyleActivityFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_life_style_activity, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val btn : Button = activity!!.findViewById(R.id.btn_life_next)

        txt_activity_title.text = keeper.name + "님은"+"\n"+"하루에 햇빛을 받으며"+"\n"+"야외활동을 얼마나 하시나요?"

        setColorInPartitial()

        if(keeper.activity?:-1 == -1){
            "여기1".logDebug()
            rg_activity_1.clearCheck()
            btn.isEnabled = false
            btn.setTextColor(resources.getColor(R.color.colorWhite))
        }else{
            "여기2".logDebug()
            rg_activity_1.check(keeper.activity!!)
            btn.isEnabled = true
            btn.setTextColor(resources.getColor(R.color.colorPrimary))
        }

        rg_activity_1.setOnCheckedChangeListener { radioGroup, i ->
            keeper.activity = rg_activity_1.checkedRadioButtonId
            Log.v("YGYG", rg_activity_1.checkedRadioButtonId.toString())
            btn.isEnabled = true
            btn.setTextColor(resources.getColor(R.color.colorPrimary))
        }

    }

    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_activity_title?.text.toString())
        val start : Int = keeper.name!!.length + 15


        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            start,
            start + 4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_activity_title?.text = builder
    }

}
