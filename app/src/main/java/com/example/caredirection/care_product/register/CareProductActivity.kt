package com.example.caredirection.care_product.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.care_product.CareRegisterComplete
import com.example.caredirection.care_product.search.SearchCareProductActivity
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvCareProductRegisterData
import com.example.caredirection.data.network.HomCareProductData
import com.example.caredirection.network.RequestURL
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_care_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CareProductActivity : AppCompatActivity() {

    private lateinit var rvCareRegisterView : RecyclerView
    private lateinit var rvCareRegisterAdapter : CareProductRegisterAdapter
    private lateinit var keeper: ResearchKeeper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_product)

        keeper = ResearchKeeper(this)

        rvCareRegisterView=findViewById(R.id.rv_care_register_view)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvCareRegisterView)

        rvCareRegisterView.layoutManager=LinearLayoutManager(this)
        rvCareRegisterAdapter=
            CareProductRegisterAdapter(this)
        rvCareRegisterView.adapter=rvCareRegisterAdapter
        snapHelper.attachToRecyclerView(rvCareRegisterView)

        // 복용제품리스트 통신
        getHomCareProductResponse()

        btn_care_register_product.setOnClickListener {
            startActivity(Intent(this, SearchCareProductActivity::class.java))
        }

        btn_care_register_product_next.setOnClickListener {
            keeper.careProductAdd = 1
            startActivity(Intent(this, CareRegisterComplete::class.java))
        }
    }

    private fun getHomCareProductResponse() {
        val call: Call<HomCareProductData> =
            RequestURL.service.getCareProductList(
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI",
                date = "2020-01-04"
            )
        call.enqueue(object : Callback<HomCareProductData> {
            override fun onFailure(call: Call<HomCareProductData>, t: Throwable) {
                t.toString().logDebug()
            }

            override fun onResponse(
                call: Call<HomCareProductData>,
                response: Response<HomCareProductData>
            ) {
                val careProducts = mutableListOf<RvCareProductRegisterData>()
                if (response.isSuccessful) {
                    response.message()
                    val productRespo = response.body()!!
                    productRespo.toString().logDebug()
                    for (item in productRespo.data) {
                        careProducts.add(
                            RvCareProductRegisterData(
                                item.product_remain,
                                item.image_location,
                                item.product_company_name,
                                item.product_is_import,
                                item.product_name,
                                item.product_price,
                                item.product_price_per_unit,
                                item.product_quantity
                            )
                        )
                        item.product_idx.toString().logDebug()
                    }

                    rvCareRegisterAdapter.data = careProducts
                    rvCareRegisterAdapter.notifyDataSetChanged()

                }
            }

        })


    }
}
