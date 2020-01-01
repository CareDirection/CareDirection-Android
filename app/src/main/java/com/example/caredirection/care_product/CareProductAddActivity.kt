package com.example.caredirection.care_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_care_product_add.*

class CareProductAddActivity : AppCompatActivity() {

    private lateinit var product_add_medicine: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_product_add)

        makeController()
    }

    private fun makeController(){
        val medicine = arrayOf(1,2,3,4,5,6,7,8,9,10)
        val medicineAdapter = ArrayAdapter(
            this,
            R.layout.spinner_product_search_item,
            medicine
        )
        //categoryAdapter.setDropDownViewResource(R.layout.fragment_product_search)
        sp_product_add.adapter = medicineAdapter

        sp_product_add.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //함량 변수에 저장
                    product_add_medicine = medicine[position].toString()+"정"

                }
            }
    }
}
