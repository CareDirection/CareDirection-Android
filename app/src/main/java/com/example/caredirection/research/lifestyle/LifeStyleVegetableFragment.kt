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
import kotlinx.android.synthetic.main.fragment_life_style_vegetable.*

/**
 * A simple [Fragment] subclass.
 */
class LifeStyleVegetableFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_life_style_vegetable, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val btn : Button = activity!!.findViewById(R.id.btn_life_next)

        txt_vegetable_title.text = keeper.name + "님은"+"\n"+"일주일에 몇 회 이상"+"\n"+"녹황색 채소를 섭취하시나요?"

        setColorInPartitial()

        if(keeper.vegetable?:-1 == -1){
            rg_vegetable_1.clearCheck()
            btn.isEnabled = false
            btn.setTextColor(resources.getColor(R.color.colorWhite))
        }else{
            rg_vegetable_1.check(keeper.vegetable!!)
            btn.isEnabled = true
            btn.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        rg_vegetable_1.setOnCheckedChangeListener { radioGroup, i ->
            keeper.vegetable = rg_vegetable_1.checkedRadioButtonId
            Log.v("YGYG", rg_vegetable_1.checkedRadioButtonId.toString())
            btn.isEnabled = true
            btn.setTextColor(resources.getColor(R.color.colorPrimary))
        }

    }

    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_vegetable_title?.text.toString())
        val start : Int = keeper.name!!.length + 15


        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            start,
            start + 6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_vegetable_title?.text = builder
    }
}
