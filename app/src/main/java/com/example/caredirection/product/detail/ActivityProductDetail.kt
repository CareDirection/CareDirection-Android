package com.example.caredirection.product.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.ProductDetailData
import com.example.caredirection.data.network.ProductDetailLowest
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_product_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityProductDetail : AppCompatActivity() {

    private lateinit var rv_product_detail: RecyclerView
    private lateinit var rv_product_detail_adapter: ProductDetailAdapter
    private var category = mutableListOf<String>()
    private var categoryPrice = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        getProductDailData()
        initList()
    }

    //spinner_activity_product_detail_per 스피너 아이디

    private fun initList() {

        rv_product_detail_adapter = ProductDetailAdapter(this@ActivityProductDetail)
        rv_product_detail = findViewById(R.id.rv_product_detail_item)

        rv_product_detail.layoutManager = LinearLayoutManager(this@ActivityProductDetail)
        getProductDetailLow()
       /* rv_product_detail_adapter.data = listOf(
            ProductDetailAdapter.RvProductDetailData(
                "쿠팡",
                "16,920 원",
                "(1일 188원)",
                "https://hydok.tistory.com/34"
            ),
            ProductDetailAdapter.RvProductDetailData("쿠팡", "16,920 원", "(1일 188원)", "sopt.org"),
            ProductDetailAdapter.RvProductDetailData("쿠팡", "16,920 원", "(1일 188원)", "facebook.com")
        )*/


        rv_product_detail.adapter = rv_product_detail_adapter


        //val category = arrayOf("30정","90정", "180정")

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

    private fun getProductDailData() {
        val call: Call<ProductDetailData> = RequestURL.service.getProductDetailData(
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MjQsImlhdCI6MTU3Nzg3NzY1NiwiZXhwIjo4Nzk3Nzg3NzY1NiwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.WysKIH3-qDf3GTR-RKKl23hp_9byodzDm7TdISMTkmk",
            product_idx = "6"
        )

        call.enqueue(
            object : Callback<ProductDetailData> {
                override fun onFailure(call: Call<ProductDetailData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductDetailData>,
                    response: Response<ProductDetailData>
                ) {
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
                    //endregion detailcontent
                }
            }
        )
    }

    private fun getProductDetailLow() {
        val call: Call<ProductDetailLowest> = RequestURL.service.getProductDetailLow(
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MjQsImlhdCI6MTU3Nzg3NzY1NiwiZXhwIjo4Nzk3Nzg3NzY1NiwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.WysKIH3-qDf3GTR-RKKl23hp_9byodzDm7TdISMTkmk",
            product_idx = "6"
        )
        call.enqueue(
            object : Callback<ProductDetailLowest> {
                override fun onFailure(call: Call<ProductDetailLowest>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductDetailLowest>,
                    response: Response<ProductDetailLowest>
                ) {
                    val ProductDetailLow: ProductDetailLowest = response.body()!!


                    (0 until ProductDetailLow.data.size!!).forEach {
                        rv_product_detail_adapter.data.add(ProductDetailLow.data[it])
                        rv_product_detail_adapter.notifyDataSetChanged()
                    }


                }
            })
    }
}
