package com.example.caredirection.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvEssentialGraphData
import com.example.caredirection.data.network.HomeGraphData
import com.example.caredirection.data.network.HomeGraphDetailData
import com.example.caredirection.home.essential.EssentialGraphAdapter
import com.example.caredirection.network.RequestURL
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_graph_detail.*
import kotlinx.android.synthetic.main.activity_graph_detail.top_bar
import kotlinx.android.synthetic.main.activity_graph_detail.view.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GraphDetailsActivity : AppCompatActivity() {

    val listData = ArrayList<BarEntry>()
    val listData2 = ArrayList<BarEntry>()
    val listData3 = ArrayList<BarEntry>()
    private lateinit var barEntry: Array<Float>
    private lateinit var xLabelIngredients2: Array<String>
    private lateinit var xLabelIngredients1: Array<String>
    private lateinit var xLabelIngredients3: Array<String>
    private lateinit var rvEssentialGraphAdapter: EssentialGraphAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_detail)

        val spinnerHomeEssentialArray =
            resources.getStringArray(R.array.spinner_home_essential_items)
        val spinnerHomeEssentialArrayAdapter =
            ArrayAdapter(this, R.layout.spinner_home_essential, spinnerHomeEssentialArray)


        top_bar.top_plain_title.txt_top_bar_title.text="필수 비타민 & 미네랄"



        //밑에 라벨 //TODO 네이밍 다시 하기
        xLabelIngredients2 =
            arrayOf("비타민 A", "비타민", "B", "C", "D", "E", "A3", "B1", "C2", "D3", "E4")
        xLabelIngredients1 =
            arrayOf("비타민 A", "비타민", "B", "C", "D", "E", "A3", "B1", "C2", "D3", "E4")
//        setChart(listData, xLabelIngredients)
        getHomeGraphResponse()

        //스피너 초기 설정
        spinner_home_graph_detaill.dropDownVerticalOffset = 4 //스피너 드롭다운 위치 설정
        spinner_home_graph_detaill.adapter = spinnerHomeEssentialArrayAdapter
        //스피너 초기값은 0 (즉, 낮은 순)
        //homeFragmentView.spinner_home_essential.setSelection(0)
        //homeFragmentView.spinner_home_essential.isSelected=false
        spinner_home_graph_detaill.setSelection(0)
//        homeFragmentView.spinner_home_essential.setSelection(0,false)
        //homeFragmentView.spinner_home_essential.isSelected=true

        spinner_home_graph_detaill.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //스피너 설정 값에 따라서 선택정렬해줌
                    //그럼 그려주는 순서가 바뀐다
                    when (position) {
                        0 -> {
                            setChart(listData, xLabelIngredients1)
                        }
                        1 -> {

                            //낮은 순
                            for (i in 0 until listData2.size - 1) {
                                var min = i
                                for (j in i + 1 until listData2.size) {
                                    if (listData2[j].y <= listData2[min].y) {
                                        min = j
                                    }
                                }
                                if (i != min) {

                                    //순서 선택정렬
                                    var temp = listData2[i].y
                                    listData2[i].y = listData2[min].y
                                    listData2[min].y = temp

                                    //매칭되어있는 라벨도 함께 이동
                                    var tempX = xLabelIngredients2[i]
                                    xLabelIngredients2[i] = xLabelIngredients2[min]
                                    xLabelIngredients2[min] = tempX
                                }
                            }
                            setChart(listData2, xLabelIngredients2)

                        }
                        2 -> {
                            //높은 순
//                        listData.sortByDescending {
//                            it.y
//                        }

                            for (i in 0 until listData2.size - 1) {
                                //선택정렬 높은순으로 합니다
                                var min = i
                                for (j in i + 1 until listData2.size) {
                                    if (listData2[j].y >= listData2[min].y) {
                                        min = j
                                    }
                                }

                                if (i != min) {
                                    var temp = listData2[i].y
                                    listData2[i].y = listData2[min].y
                                    listData2[min].y = temp

                                    //라벨도 같이 움직입니다.
                                    var tempX = xLabelIngredients2[i]
                                    xLabelIngredients2[i] = xLabelIngredients2[min]
                                    xLabelIngredients2[min] = tempX
                                }
                            }

                            setChart(listData2, xLabelIngredients2)

                        }

                    }
                }

            }

        rv_graph_detail_view.layoutManager = LinearLayoutManager(this)
        rvEssentialGraphAdapter = EssentialGraphAdapter(this)
        rv_graph_detail_view.adapter = rvEssentialGraphAdapter
        //rvEssentialGraphAdapter.initLineChart()
        //rvEssentialGraphAdapter.setChart(listData,xLabelIngredients1)
        initLineBar()
        getGraphDetailedResponse()
    }
    private fun setChart(listData: ArrayList<BarEntry>, xLabelIngredients: Array<String>) {
        val dataSet = BarDataSet(listData, "")

        val listColor = ArrayList<Int>()
//        listColor.add(ContextCompat.getColor(context!!, R.color.colorBlueGraph))
//        listColor.add(ContextCompat.getColor(context!!, R.color.colorRedGraph))

        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//                Log.v("YGYG", vitamins[value.toInt()])

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

        chart_home_detail.legend.isEnabled = false// 색 블럭 그룹핑할떄 이색은 이 그룹이다

        chart_home_detail.description.isEnabled = false


        val lineData = BarData(dataSet)
        chart_home_detail.data = lineData
        chart_home_detail.setFitBars(true)
        chart_home_detail.setTouchEnabled(true)//됨
        chart_home_detail.isHorizontalScrollBarEnabled = true
        //chart.isDragXEnabled=true
        chart_home_detail.isDragDecelerationEnabled = true
        // chart.setDragXEnabled(true)
        //chart.setHorizontalScrollBarEnabled(true)
        chart_home_detail.isDoubleTapToZoomEnabled = false//두번터치하고 스크롤은 됨
        chart_home_detail.isDragXEnabled = true
        chart_home_detail.setVisibleXRange(3f, 6f) // X에 그려줄 최소, 최대 단위 정하기
        chart_home_detail.animateY(1000) //세로축 에니메이션
        // chart.data = lineData

        val xAxis = chart_home_detail.xAxis
        xAxis.valueFormatter = formatter

        chart_home_detail.invalidate()
    }
    private fun initLineBar() {

        val xAxis = chart_home_detail.xAxis
//        xAxis.setDrawLabels(false) 이것이 바로 라벨을 지워주는 친구였다 ex) 비타민 D 같은 것들
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f // 라벨 써주는 간격 조정
        // xAxis.mLabelRotatedWidth=10//이건 뭐지
        //이거하면 죽음  xAxis.spaceMax=5f

        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        val rightYAxis = chart_home_detail.axisRight
        rightYAxis.isEnabled = false
        //rightYAxis.mAxisMinimum=0f
        //rightYAxis.mAxisMaximum=120f
        /*
        rightYAxis.granularity= 40f // 라벨 간격 조정
        rightYAxis.setDrawLabels(false)
        rightYAxis.setDrawGridLines(false)
        rightYAxis.setDrawAxisLine(false)
        */
        //y축
        val leftYAxis = chart_home_detail.axisLeft
        leftYAxis.setAxisMaximum(120f)
        leftYAxis.setAxisMinimum(0f)
        //leftYAxis.mAxisMinimum=0f
        //leftYAxis.mAxisMaximum=120f
        leftYAxis.granularity = 20f //라벨 써주는 간격 조정
        leftYAxis.setDrawLabels(false) //todo 기-디한테 라벨 써주는지 아닌지 물어보기
        leftYAxis.setDrawGridLines(false)
        leftYAxis.setDrawAxisLine(false)

        //상한선 그려주기
        val ll1 = LimitLine(100f, "")
        ll1.lineWidth = 3f
        ll1.enableDashedLine(50f, 20f, 0f)
        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)
        //ll1.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
        //ll1.lineColor=R.color.colorRed 이렇게 하니까 안먹음 getcolor 사용해야함
        ll1.lineColor = ContextCompat.getColor(this, R.color.colorRed)

        ll1.textSize = 10f
        // ll1.lineColor=R.color.colorGrey

        val ll2 = LimitLine(30f, "")
        ll2.lineWidth = 3f//선의 굵기
        //ll2.lineColor=R.color.colorPrimary //Todo
        ll2.lineColor = ContextCompat.getColor(this, R.color.colorPrimary)
        ll2.enableDashedLine(50f, 20f, 0f)
        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)

        // ll2.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
        ll2.textSize = 10f

        leftYAxis.addLimitLine(ll1)  // 상한선 그리기
        leftYAxis.addLimitLine(ll2)
    }


    //홈뷰 - 그래프 통신
    private fun getHomeGraphResponse() {
        val call: Call<HomeGraphData> =
            RequestURL.service.getHomeGraph("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjMsImlhdCI6MTU3ODAyODU0OSwiZXhwIjo4Nzk3ODAyODU0OSwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.55DCPnT20acoLi7D9ajK9SRWdF3HxsxFlKx-quHS3oU")
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
                    //TODO 차트에 그려줄 퍼센츠와 라벨 가져오기
                    for (i in 0..10) {
                        barEntry = arrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
                        barEntry[i] = graphResponse.data[i].nutrient_percent.toFloat()
                        listData.add(BarEntry(i.toFloat(), barEntry[i]))
                        listData2.add(BarEntry(i.toFloat(), barEntry[i]))

                        xLabelIngredients2[i] = graphResponse.data[i].nutrient_name
                        xLabelIngredients1[i] = graphResponse.data[i].nutrient_name
                    }

                    setChart(listData,xLabelIngredients1)

                }

            }
        )
    }

    private fun getGraphDetailedResponse(){
        val call: Call<HomeGraphDetailData> =
            RequestURL.service.getGreaphDetailed("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjMsImlhdCI6MTU3ODAyODU0OSwiZXhwIjo4Nzk3ODAyODU0OSwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.55DCPnT20acoLi7D9ajK9SRWdF3HxsxFlKx-quHS3oU")
        call.enqueue(
            object:Callback<HomeGraphDetailData>{
                override fun onFailure(call: Call<HomeGraphDetailData>, t: Throwable) {
                    t.toString().logDebug()
                }

                override fun onResponse(
                    call: Call<HomeGraphDetailData>,
                    response: Response<HomeGraphDetailData>
                ) {
                    val details = mutableListOf<RvEssentialGraphData>()
                    if(response.isSuccessful) {
                        val detailedGraphRepos = response.body()!!.data
                        for (item in detailedGraphRepos) {
                            details.add(
                                RvEssentialGraphData(
                                    item.nutrient_name,
                                    item.description,
                                    item.my_change_value_description,
                                    item.my_current_value_percent.toFloat()

                                )
                            )
                           // rvEssentialGraphAdapter.initLineChart()
                            listData3.add(BarEntry(0f,item.my_current_value_percent.toFloat()))
                            //rvEssentialGraphAdapter.setChart(listData3,xLabelIngredients1)
                         }
                    }
                    rvEssentialGraphAdapter.data=details
                    rvEssentialGraphAdapter.notifyDataSetChanged()
                }


            }
        )


    }
}
