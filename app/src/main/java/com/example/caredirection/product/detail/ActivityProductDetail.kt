package com.example.caredirection.product.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.CommonData
import com.example.caredirection.data.network.ProductDetailData
import com.example.caredirection.data.network.ProductDetailLowest
import com.example.caredirection.data.network.ProductStandardData
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.dialog_product_standard_explation.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ActivityProductDetail : AppCompatActivity() {

    private lateinit var content_title1: String
    private lateinit var content_txt1: String

    private lateinit var content_title2: String
    private lateinit var content_txt2: String

    private lateinit var content_title3: String
    private lateinit var content_txt3: String

    private lateinit var rv_product_detail: RecyclerView
    private lateinit var rv_product_detail_adapter: ProductDetailAdapter
    private var category = mutableListOf<String>()
    private var categoryPrice = mutableListOf<String>()

    private lateinit var product_number: String
    private lateinit var search_product_idx: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        try {
            product_number = intent.getStringExtra("name")!!.toString()
            getProductDailData(product_number)


        } catch (e: Exception) {
            search_product_idx = intent.getStringExtra("search_product_idx")!!.toString()
            getProductDailData(search_product_idx)
        }
        try {

        } catch (e: Exception) {

        }

        initList()
    }

    private fun contentDialog(content_title: String, content_txt: String) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_explation, null)

        //dialogView.txt_dialog_product_standard_explain.tex

        ///여기 하기
        dialogView.txt_dialog_product_standard_content.text = content_title
        dialogView.txt_dialog_product_standard_explain.text = content_txt

        val dialog = builder.setView(dialogView).show()

        dialogView.txt_dialog_product_standard_close.setOnClickListener {
            dialog.dismiss()
        }
    }
    //spinner_activity_product_detail_per 스피너 아이디

    private fun initList() {

        rv_product_detail_adapter = ProductDetailAdapter(this@ActivityProductDetail)
        rv_product_detail = findViewById(R.id.rv_product_detail_item)

        rv_product_detail.layoutManager = LinearLayoutManager(this@ActivityProductDetail)



        rv_product_detail.adapter = rv_product_detail_adapter


        val categoryAdapter =
            ArrayAdapter(
                this@ActivityProductDetail,
                R.layout.spinner_product_search_item,
                R.id.spinner_item,
                category
            )

        //region 스피너
        //categoryAdapter.setDropDownViewResource(R.layout.spinner_product_search_item)


        spinner_activity_product_detail_per.apply {
            adapter = categoryAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("test", "selected - postition : $position")
                    when (position) {
                        position -> {
                            Log.d("test", categoryPrice[position])
                            txt_activity_product_detail_content_price2.text =
                                categoryPrice[position]
                        }
                    }
                }
            }
        }
    }

    private fun dialogShow() {
        cardview_activity_product_detail1.setOnClickListener {
            contentDialog(content_title1, content_txt1)
        }
        cardview_activity_product_detail2.setOnClickListener {
            contentDialog(content_title2, content_txt2)
        }
        cardview_activity_product_detail3.setOnClickListener {
            contentDialog(content_title3, content_txt3)
        }
    }

    private fun getProductDailData(product_number: String) {
        val call: Call<ProductDetailData> = RequestURL.run {
            service.getProductDetailData(
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MjQsImlhdCI6MTU3Nzg3NzY1NiwiZXhwIjo4Nzk3Nzg3NzY1NiwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.WysKIH3-qDf3GTR-RKKl23hp_9byodzDm7TdISMTkmk",
                product_idx = product_number
            )
        }

        call.enqueue(
            object : Callback<ProductDetailData> {
                override fun onFailure(call: Call<ProductDetailData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductDetailData>,
                    response: Response<ProductDetailData>
                ) {
                    if (response.isSuccessful) {
                        val ProductDetailDataList: ProductDetailData = response.body()!!

                        (0 until ProductDetailDataList.data.size - 1!!).forEach {
                            //                        Log.d("잘대대대",ProductDetailDataList.data[it].count_price.product_quantity_price.toString())
                            categoryPrice.add(ProductDetailDataList.data[it].count_price.product_quantity_price.toString())
                            category.add(ProductDetailDataList.data[it].count_price.product_quantity_count.toString())
                        }


                        //region detailcontent
                        Glide.with(this@ActivityProductDetail)
                            .load(ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.image_key)
                            .centerCrop()
                            .into(img_activity_product_detail)

                        content_title1 =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard1
                        content_txt1 =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_features_name

                        content_title2 =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard2
                        content_txt2 =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_additives

                        content_title3 =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard3
                        content_txt3 =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_cautions


                        txt_activity_product_detail_content_ename.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_company_name
                        txt_activity_product_detail_content_kname.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_name
                        txt_activity_product_detail_content2.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard1
                        txt_activity_product_detail_absorption2.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard2
                        txt_activity_product_detail_citation1.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard3
                        txt_activity_product_detail_content1.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard1_value
                        txt_activity_product_detail_absorption1.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard2_value
                        txt_activity_product_detail_citation1.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_standard3_value
                        txt_product_detail_character_content.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_features_name
                        txt_product_detail_intake_content.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_daily_dose
                        txt_product_detail_per_intake_content1.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_detail_name
                        txt_product_detail_per_intake_content2.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_detail_value
                        txt_product_detail_content_content.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_additives
                        txt_product_detail_warning_content.text =
                            ProductDetailDataList.data[ProductDetailDataList.data.size - 1].common_data.product_cautions

                        getProductDetailLow(product_number)
                        getProductStandard(product_number.toInt())
                        //endregion detailcontent
                        dialogShow()
                    }
                }
            }
        )

    }

    private fun getProductStandard(position: Int) {
        val call: Call<ProductStandardData> =
            RequestURL.service.getProductStandard(product_idx = position.toString())
        call.enqueue(
            object : Callback<ProductStandardData> {
                override fun onFailure(call: Call<ProductStandardData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductStandardData>,
                    response: Response<ProductStandardData>
                ) {
                    if (response.isSuccessful) {
                        val ProductStandardnutrient: ProductStandardData = response.body()!!


                        content_txt1 = ProductStandardnutrient.data[0].standard_description
                        content_txt2 = ProductStandardnutrient.data[1].standard_description
                        content_txt3 = ProductStandardnutrient.data[2].standard_description
                        dialogShow()
                    }
                }
            })
    }

    private fun getProductDetailLow(product_idx: String) {
        val call: Call<ProductDetailLowest> = RequestURL.service.getProductDetailLow(
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI",
            product_idx = product_idx
        )
        call.enqueue(
            object : Callback<ProductDetailLowest> {
                override fun onFailure(call: Call<ProductDetailLowest>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductDetailLowest>,
                    response: Response<ProductDetailLowest>
                ) {
                    if (response.isSuccessful) {
                        val ProductDetailLow: ProductDetailLowest = response.body()!!

                        (0 until ProductDetailLow.data.size!!).forEach {
                            rv_product_detail_adapter.data.add(ProductDetailLow.data[it])
                            rv_product_detail_adapter.notifyDataSetChanged()
                        }


                    }
                }
            })


    }
}
