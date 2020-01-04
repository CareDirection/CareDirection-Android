package com.example.caredirection.product.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.network.*
import com.example.caredirection.network.RequestURL
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.dialog_product_standard_explation.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    val listData = ArrayList<BarEntry>()
    private lateinit var barEntry: Array<Float>
    private lateinit var xLabelIngredients1: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        if (intent.getStringExtra("name") != null) {
            product_number = intent.getStringExtra("name")!!.toString()
            getProductDailData(product_number)
            initLineChart()
            getProductGraphResponse(product_number.toInt())
            Log.e("test1:", product_number)
        } else if (intent.getStringExtra("search_product_idx") != null) {
            search_product_idx = intent.getStringExtra("search_product_idx")!!.toString()
            getProductDailData(search_product_idx)
            initLineChart()
            getProductGraphResponse(search_product_idx.toInt())
            Log.e("test2:", search_product_idx)
        } else {
            Log.e("test3:", "Teest")

        }
        xLabelIngredients1 =
            arrayOf("비타민 A", "비타민", "B", "C", "D", "E", "A3", "B1", "C2", "D3", "E4")
        initList()
    }

    private fun contentDialog(content_title: String, content_txt: String) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_explation, null)

        dialogView.txt_dialog_product_standard_content.text = content_title
        dialogView.txt_dialog_product_standard_explain.text = content_txt

        val dialog = builder.setView(dialogView).show()

        dialogView.txt_dialog_product_standard_close.setOnClickListener {
            dialog.dismiss()
        }
    }

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

                    when (position) {
                        position -> {
                            Log.d("testest",categoryPrice[position])

                            txt_activity_product_detail_content_price2.text = categoryPrice[position]
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
                        val productDetailDataList: ProductDetailData = response.body()!!

                        (0 until productDetailDataList.data.size-1!!).forEach {
                            //                        Log.d("잘대대대",ProductDetailDataList.data[it].count_price.product_quantity_price.toString())
                            categoryPrice.add(productDetailDataList.data[it].count_price.product_quantity_price.toString())
                            category.add(productDetailDataList.data[it].count_price.product_quantity_count.toString())
                            Log.d("category_test", productDetailDataList.data[it].count_price.product_quantity_count.toString())
                            //스피너 함수 여기서 선언

                        }
                        initList()

                        //region detailcontent
                        Glide.with(this@ActivityProductDetail)
                            .load(productDetailDataList.data[productDetailDataList.data.size - 1].common_data.image_key)
                            .centerCrop()
                            .into(img_activity_product_detail)

                        content_title1 =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard1
                        content_txt1 =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_features_name

                        content_title2 =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard2
                        content_txt2 =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_additives

                        content_title3 =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard3
                        content_txt3 =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_cautions


                        txt_activity_product_detail_content_ename.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_company_name
                        txt_activity_product_detail_content_kname.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_name
                        txt_activity_product_detail_content2.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard1
                        txt_activity_product_detail_absorption2.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard2
                        txt_activity_product_detail_citation1.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard3
                        txt_activity_product_detail_content1.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard1_value
                        txt_activity_product_detail_absorption1.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard2_value
                        txt_activity_product_detail_citation1.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_standard3_value
                        txt_product_detail_character_content.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_features_name
                        txt_product_detail_intake_content.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_daily_dose
                        txt_product_detail_per_intake_content1.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_detail_name
                        txt_product_detail_per_intake_content2.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_detail_value
                        txt_product_detail_content_content.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_additives
                        txt_product_detail_warning_content.text =
                            productDetailDataList.data[productDetailDataList.data.size - 1].common_data.product_cautions


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
                        val productStandardnutrient: ProductStandardData = response.body()!!


                        content_txt1 = productStandardnutrient.data[0].standard_description
                        content_txt2 = productStandardnutrient.data[1].standard_description
                        content_txt3 = productStandardnutrient.data[2].standard_description
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

                        (0 until ProductDetailLow.data.size).forEach {
                            rv_product_detail_adapter.data.add(ProductDetailLow.data[it])
                            rv_product_detail_adapter.notifyDataSetChanged()
                        }
                    }
                }
            })
    }

    private fun initLineChart() {

        val xAxis = chart_product_detail.xAxis

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f // 라벨 써주는 간격 조정

        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        val rightYAxis = chart_product_detail.axisRight
        rightYAxis.isEnabled = false

        val leftYAxis = chart_product_detail.axisLeft
        leftYAxis.axisMaximum = 120f
        leftYAxis.axisMinimum = 0f
        leftYAxis.granularity = 20f //라벨 써주는 간격 조정
        leftYAxis.setDrawLabels(false) //todo 기-디한테 라벨 써주는지 아닌지 물어보기
        leftYAxis.setDrawGridLines(false)
        leftYAxis.setDrawAxisLine(false)

        val ll1 = LimitLine(100f, "")
        ll1.lineWidth = 3f
        ll1.enableDashedLine(50f, 20f, 0f)
        ll1.lineColor = ContextCompat.getColor(this, R.color.colorRed)

        ll1.textSize = 10f

        val ll2 = LimitLine(30f, "")
        ll2.lineWidth = 3f//선의 굵기
        ll2.lineColor = ContextCompat.getColor(this, R.color.colorPrimary)
        ll2.enableDashedLine(50f, 20f, 0f)

        ll2.textSize = 10f

        leftYAxis.addLimitLine(ll1)  // 상한선 그리기
        leftYAxis.addLimitLine(ll2)
    }

    private fun setChart(
        listData: ArrayList<BarEntry>,
        xLabelIngredients: Array<String>
    ) {
        val dataSet = BarDataSet(listData, "")

        val listColor = ArrayList<Int>()

        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {

                return xLabelIngredients[value.toInt()]

            }
        }


        listData.forEach {
            if (it.y > 100.0f || it.y < 30.0f)
                listColor.add(ContextCompat.getColor(this, R.color.colorRedGraph))
            else
                listColor.add(ContextCompat.getColor(this, R.color.colorBlueGraph))
        }

        dataSet.colors = listColor

        dataSet.valueTextColor = ContextCompat.getColor(this, android.R.color.black)

        dataSet.setDrawValues(false)

        chart_product_detail.legend.isEnabled = false// 색 블럭 그룹핑할떄 이색은 이 그룹이다

        chart_product_detail.description.isEnabled = false


        val lineData = BarData(dataSet)
        chart_product_detail.data = lineData
        chart_product_detail.setFitBars(true)
        chart_product_detail.setTouchEnabled(true)//됨
        chart_product_detail.isHorizontalScrollBarEnabled = true
        chart_product_detail.isDragDecelerationEnabled = true
        chart_product_detail.isDoubleTapToZoomEnabled = false//두번터치하고 스크롤은 됨
        chart_product_detail.isDragXEnabled = true
        chart_product_detail.setVisibleXRange(3f, 6f) // X에 그려줄 최소, 최대 단위 정하기
        chart_product_detail.animateY(1000) //세로축 에니메이션

        val xAxis = chart_product_detail.xAxis
        xAxis.valueFormatter = formatter

        chart_product_detail.invalidate()
    }

    private fun getProductGraphResponse(product_position: Int) {
        val call: Call<HomeGraphData> =
            RequestURL.service.getProductDetailGraph(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI",
                product_position
            )
        call.enqueue(
            object : Callback<HomeGraphData> {
                override fun onFailure(call: Call<HomeGraphData>, t: Throwable) {
                    t.toString().logDebug()
                }

                override fun onResponse(
                    call: Call<HomeGraphData>,
                    response: Response<HomeGraphData>
                ) {
                    val graphResponse: HomeGraphData = response.body()!!
                    for (i in 0..10) {
                        barEntry = arrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
                        barEntry[i] = graphResponse.data[i].nutrient_percent.toFloat()
                        listData.add(BarEntry(i.toFloat(), barEntry[i]))

                        xLabelIngredients1[i] = graphResponse.data[i].nutrient_name
                    }
                    initLineChart()
                    setChart(listData, xLabelIngredients1)

                }

            }
        )
    }
}
