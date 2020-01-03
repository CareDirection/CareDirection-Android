package com.example.caredirection.care_product.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvSearchCareProductData
import com.example.caredirection.data.network.CareProductSearchData
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_survey_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCareProductActivity :AppCompatActivity(){

    private lateinit var searchProduct: String
    private var count : Int = 0
    private lateinit var rv_servey_serach: RecyclerView
    private lateinit var rv_servey_serach_adapter: SearchCareProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_search)

        rv_servey_serach_adapter = SearchCareProductAdapter(this)

        rv_servey_serach = findViewById(R.id.rv_activity_survey_search)
        rv_servey_serach.layoutManager = LinearLayoutManager(this)

        rv_servey_serach.adapter = rv_servey_serach_adapter

        img_activity_survey_search.setOnClickListener{
            searchProduct = edt_activity_survey.text.toString()
            // 복용제품 검색리스트 통신
            getCareProductSearchList()
        }
    }

    private fun getCareProductSearchList() {
        val call: Call<CareProductSearchData> =
            RequestURL.service.getCareProductSearchList(
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI",
                date = searchProduct
            )
        call.enqueue(object : Callback<CareProductSearchData> {
            override fun onFailure(call: Call<CareProductSearchData>, t: Throwable) {
                t.toString().logDebug()
            }

            override fun onResponse(
                call: Call<CareProductSearchData>,
                response: Response<CareProductSearchData>
            ) {
                val careProducts = mutableListOf<RvSearchCareProductData>()
                if (response.isSuccessful) {
                    response.message()
                    val productRespo = response.body()!!
                    productRespo.toString().logDebug()
                    for (item in productRespo.data) {
                        careProducts.add(
                            RvSearchCareProductData(
                                item.image_location,
                                item.product_name,
                                item.product_company_name,
                                item.product_is_import,
                                item.product_price,
                                item.product_price_per_unit,
                                item.product_quantity,
                                item.product_is_already_managed
                            )
                        )
                    }
                    rv_servey_serach_adapter.data = careProducts
                    rv_servey_serach_adapter.notifyDataSetChanged()

                }
            }

        })


    }
}