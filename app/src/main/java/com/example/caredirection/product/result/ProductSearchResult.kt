package com.example.caredirection.product.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.network.Data
import com.example.caredirection.data.network.ProductSearchContentData
import com.example.caredirection.network.RequestURL
import com.example.caredirection.product.detail.ProductDetailAdapter
import kotlinx.android.synthetic.main.activity_product_search_result.*
import kotlinx.android.synthetic.main.fragment_product_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductSearchResult : AppCompatActivity() {

    private lateinit var rv_product_search_result: RecyclerView
    private lateinit var rv_product_search_result_adapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search_result)

        val category = arrayOf("제품", "성분")
        val categoryAdapter =
            ArrayAdapter(this@ProductSearchResult, R.layout.spinner_product_search_item, category)

        //region 스피너
        categoryAdapter.setDropDownViewResource(R.layout.spinner_product_search_item)
        spinner_activity_product_search_result.adapter = categoryAdapter

        spinner_activity_product_search_result.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {

                    }
                }
            }
        //endregion 스피너

        init()
    }

    private fun init() {
        rv_product_search_result_adapter = SearchResultAdapter(this@ProductSearchResult)
        rv_product_search_result = findViewById(R.id.rv_activity_product_search_result)

        rv_product_search_result.layoutManager = LinearLayoutManager(this@ProductSearchResult)

        img_activity_product_search.setOnClickListener{
            getProductSearchContent(edt_activity_product_search_result.text.toString())
        }

       /* rv_product_search_result_adapter.data = listOf(
            RvSearchResultData("쿠팡", "16,920 원", "(1일 188원)"),
            RvSearchResultData("쿠팡", "16,920 원", "(1일 188원)"),
            RvSearchResultData("쿠팡", "16,920 원", "(1일 188원)"),
            RvSearchResultData("쿠팡", "16,920 원", "(1일 188원)"),
            RvSearchResultData("쿠팡", "16,920 원", "(1일 188원)")
        )*/

        rv_product_search_result.adapter = rv_product_search_result_adapter
    }

    private fun getProductSearchContent(search_word: String) {
        val call: Call<ProductSearchContentData> =
            RequestURL.service.getProductList(search_word, "product")

        call.enqueue(
            object : Callback<ProductSearchContentData> {
                override fun onFailure(call: Call<ProductSearchContentData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductSearchContentData>,
                    response: Response<ProductSearchContentData>
                ) {
                    val productSearchcontentList : Data = response.body()!!.data

                    (0 until productSearchcontentList.searchList.size!!).forEach {
                        rv_product_search_result_adapter.data.add(productSearchcontentList.searchList[it])
                        rv_product_search_result_adapter.notifyDataSetChanged()
                    }
                }
            })
    }
}