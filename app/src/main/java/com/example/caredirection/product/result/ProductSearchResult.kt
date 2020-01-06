package com.example.caredirection.product.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.network.Data
import com.example.caredirection.data.network.ProductSearchContentData
import com.example.caredirection.network.RequestURL
import com.example.caredirection.product.detail.ProductDetailAdapter
import com.example.caredirection.product.standard.ActivityProductStandard
import kotlinx.android.synthetic.main.activity_product_search_result.*
import kotlinx.android.synthetic.main.fragment_product_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProductSearchResult : AppCompatActivity() {

    private lateinit var rv_product_search_result: RecyclerView
    private lateinit var rv_product_search_result_adapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search_result)

        val category = arrayOf("제품", "성분")
        val categoryAdapter =
            ArrayAdapter(this@ProductSearchResult, R.layout.spinner_product_search_item, category)
/*
        if(txt_activity_product_search_result_number.text.toString() == "0"){
            txt_activity_product_search_empty.visibility = View.VISIBLE
        }
        else if(txt_activity_product_search_result_number.text.toString() != "0"){
            txt_activity_product_search_empty.visibility = View.INVISIBLE
        }*/

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

                        0 -> {
                            img_activity_product_search.setOnClickListener {
                                try {
                                    getProductSearchContent(edt_activity_product_search_result.text.toString())

                                } catch (e: Exception) {
                                    Toast.makeText(
                                        this@ProductSearchResult,
                                        "올바른 값을 입력하시오",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                        }
                        1 -> {
                            img_activity_product_search.setOnClickListener {
                                try {

                                } catch (e: Exception) {
                                    Toast.makeText(
                                        this@ProductSearchResult,
                                        "올바른 값을 입력하시오",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                val intent = Intent(
                                    this@ProductSearchResult,
                                    ActivityProductStandard::class.java
                                )

                                intent.putExtra(
                                    "name",
                                    edt_activity_product_search_result.text.toString()
                                )

                                startActivity(intent, null)
                                finish()
                            }
                        }
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

        rv_product_search_result.adapter = rv_product_search_result_adapter

        edt_activity_product_search_result.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getProductSearchContent(edt_activity_product_search_result.text.toString())
            }
            true
        }
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
                    if (response.isSuccessful) {
                        val productSearchcontentList: Data = response.body()!!.data

                        (0 until productSearchcontentList.searchList.size!!).forEach {

                            rv_product_search_result_adapter.data.add(productSearchcontentList.searchList[it])
                            rv_product_search_result_adapter.notifyDataSetChanged()
                            txt_activity_product_search_result_number.text =
                                productSearchcontentList.searchList.size.toString()


                        }
                    }
                }
            })
    }
}