package com.example.caredirection.product.standard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import com.example.caredirection.R
import kotlinx.android.synthetic.main.dialog_product_standard_filter.*
import kotlinx.android.synthetic.main.fragment_product_search.view.*

class DialogProductStandardFilter : AppCompatActivity() {



    private lateinit var filter_product_standard_absorption: String
    private lateinit var filter_product_standard_citation: String
    private lateinit var filter_spinner_product_search: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_product_standard_filter)

        init()


    }

    private fun init() {
        val category = arrayOf("10000mg ~ 1000mg", "10000mg ~ 1000mg", "10000mg ~ 1000mg")
        val categoryAdapter = ArrayAdapter(
            this@DialogProductStandardFilter,
            R.layout.spinner_product_search_item,
            category
        )
        //categoryAdapter.setDropDownViewResource(R.layout.fragment_product_search)
        spinner_dialog_product_standard.adapter = categoryAdapter

        spinner_dialog_product_standard.onItemSelectedListener =
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
                    filter_spinner_product_search = category[position]

                }
            }


        //region 흡수율 체크박스
        //체크박스 왼쪽 클릭되었을때
        checked_dialog_product_standard_absorption_left.setOnClickListener {
            checked_dialog_product_standard_absorption_left.isChecked = true
            checked_dialog_product_standard_absorption_right.isChecked = false
            filter_product_standard_absorption =
                checked_dialog_product_standard_absorption_left.text.toString()
        }
        //체크박스 오른쪽 클릭되었을때
        checked_dialog_product_standard_absorption_right.setOnClickListener {
            checked_dialog_product_standard_absorption_left.isChecked = false
            checked_dialog_product_standard_absorption_right.isChecked = true
            filter_product_standard_absorption =
                checked_dialog_product_standard_absorption_right.text.toString()
        }
        //endregion


        //region 인증마크 체크박스
        //인증마크 체크박스
        checked_dialog_product_standard_citation_left.setOnClickListener {
            checked_dialog_product_standard_citation_right.isChecked = false
            checked_dialog_product_standard_citation_left.isChecked = true
            filter_product_standard_citation =
                checked_dialog_product_standard_citation_left.text.toString()
        }
        checked_dialog_product_standard_citation_right.setOnClickListener {
            checked_dialog_product_standard_citation_left.isChecked = false
            checked_dialog_product_standard_citation_right.isChecked = true
            filter_product_standard_citation =
                checked_dialog_product_standard_citation_right.text.toString()
        }
        //endregion


    }
}

