package com.example.caredirection.home.essential

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvEssentialGraphData
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.rv_item_graph_detail.view.*

class EssentialGraphAdapter(private val context: Context) : RecyclerView.Adapter<EssentialGraphHolder>(){


    var data = listOf<RvEssentialGraphData>()
    private lateinit var view : View

    var listData = ArrayList<BarEntry>()
    private lateinit var barEntry: Array<Float>
    private lateinit var xLabelIngredients2: Array<String>
    private lateinit var xLabelIngredients1: String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EssentialGraphHolder {
        view = LayoutInflater.from(context).inflate(R.layout.rv_item_graph_detail,parent,false)
        return EssentialGraphHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: EssentialGraphHolder, position: Int) {
        holder.bind(data[position])

    }


//
//     fun initLineChart() {
//
//        val xAxis = view.rv_chart.xAxis
////        xAxis.setDrawLabels(false) 이것이 바로 라벨을 지워주는 친구였다 ex) 비타민 D 같은 것들
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.granularity = 1f // 라벨 써주는 간격 조정
//        // xAxis.mLabelRotatedWidth=10//이건 뭐지
//        //이거하면 죽음  xAxis.spaceMax=5f
//
//        xAxis.setDrawGridLines(false)
//        xAxis.setDrawAxisLine(false)
//
//        val rightYAxis = view.rv_chart.axisRight
//        rightYAxis.isEnabled = false
//        //rightYAxis.mAxisMinimum=0f
//        //rightYAxis.mAxisMaximum=120f
//        /*
//        rightYAxis.granularity= 40f // 라벨 간격 조정
//        rightYAxis.setDrawLabels(false)
//        rightYAxis.setDrawGridLines(false)
//        rightYAxis.setDrawAxisLine(false)
//        */
//        //y축
//        val leftYAxis = view.rv_chart.axisLeft
//        leftYAxis.setAxisMaximum(120f)
//        leftYAxis.setAxisMinimum(0f)
//        //leftYAxis.mAxisMinimum=0f
//        //leftYAxis.mAxisMaximum=120f
//        leftYAxis.granularity = 20f //라벨 써주는 간격 조정
//        leftYAxis.setDrawLabels(false) //todo 기-디한테 라벨 써주는지 아닌지 물어보기
//        leftYAxis.setDrawGridLines(false)
//        leftYAxis.setDrawAxisLine(false)
//
//        //상한선 그려주기
//        val ll1 = LimitLine(100f, "")
//        ll1.lineWidth = 3f
//        ll1.enableDashedLine(50f, 20f, 0f)
//        ll1.lineColor = ContextCompat.getColor(context!!, R.color.colorRed)
//
//        ll1.textSize = 10f
//
//
//        val ll2 = LimitLine(30f, "")
//        ll2.lineWidth = 3f//선의 굵기
//        //ll2.lineColor=R.color.colorPrimary //Todo
//        ll2.lineColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
//        ll2.enableDashedLine(50f, 20f, 0f)
//        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)
//
//        // ll2.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
//        ll2.textSize = 10f
//
//         listData.add(BarEntry(0f,0f))
//        leftYAxis.addLimitLine(ll1)  // 상한선 그리기
//        leftYAxis.addLimitLine(ll2)
//    }
//
//    fun setChart(listData:List<BarEntry> , xLabelIngredients: Array<String>) {
//        val dataSet = BarDataSet(listData, "")
//
//        val listColor = ArrayList<Int>()
//        listColor.add(ContextCompat.getColor(context!!, R.color.colorBlueGraph))
//        listColor.add(ContextCompat.getColor(context!!, R.color.colorRedGraph))
//
//        val formatter = object : ValueFormatter() {
//            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
////                Log.v("YGYG", vitamins[value.toInt()])
//
//                return xLabelIngredients[value.toInt()]
//            }
//        }
//
//
//        listData.forEach {
//            if (it.y > 100.0f || it.y < 30.0f)
//                listColor.add(ContextCompat.getColor(context!!, R.color.colorRedGraph))
//            else
//                listColor.add(ContextCompat.getColor(context!!, R.color.colorBlueGraph))
//        }
//
//        dataSet.colors = listColor
//        dataSet.valueTextColor = ContextCompat.getColor(context!!, android.R.color.black)
//        dataSet.setDrawValues(false)
//
//        view.rv_chart.legend.isEnabled = false// 색 블럭 그룹핑할떄 이색은 이 그룹이다
//
//        view.rv_chart.description.isEnabled = false
//
//
//        val lineData = BarData(dataSet)
//        view.rv_chart.data = lineData
//        view.rv_chart.setFitBars(true)
//        view.rv_chart.setTouchEnabled(true)//됨
//        view.rv_chart.isHorizontalScrollBarEnabled = true
//        //chart.isDragXEnabled=true
//        view.rv_chart.isDragDecelerationEnabled = true
//        // chart.setDragXEnabled(true)
//        //chart.setHorizontalScrollBarEnabled(true)
//        view.rv_chart.isDoubleTapToZoomEnabled = false//두번터치하고 스크롤은 됨
//        view.rv_chart.isDragXEnabled = true
//        view.rv_chart.setVisibleXRange(1f, 2f) // X에 그려줄 최소, 최대 단위 정하기
//        view.rv_chart.animateY(1000) //세로축 에니메이션
//        // chart.data = lineData
//
//        val xAxis = view.rv_chart.xAxis
//        xAxis.valueFormatter = formatter
//
//        view.rv_chart.invalidate()
//    }
////
////    //홈뷰 - 그래프 통신
////    private fun getHomeGraphResponse() {
////        val call: Call<HomeGraphData> =
////            RequestURL.service.getHomeGraph("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjMsImlhdCI6MTU3ODAyODU0OSwiZXhwIjo4Nzk3ODAyODU0OSwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.55DCPnT20acoLi7D9ajK9SRWdF3HxsxFlKx-quHS3oU")
////        call.enqueue(
////            object : Callback<HomeGraphData> {
////                override fun onFailure(call: Call<HomeGraphData>, t: Throwable) {
////                    t.toString().logDebug()
////                }
////
////                override fun onResponse(
////                    call: Call<HomeGraphData>,
////                    response: Response<HomeGraphData>
////                ) {
////                    val graphResponse: HomeGraphData = response.body()!!
////                    //TODO 차트에 그려줄 퍼센츠와 라벨 가져오기
////                    for (i in 0..10) {
////                        barEntry = arrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
////                        barEntry[i] = graphResponse.data[i].nutrient_percent.toFloat()
////                        listData.add(BarEntry(i.toFloat(), barEntry[i]))
////
////
////                        xLabelIngredients1[i] = graphResponse.data[i].nutrient_name
////                    }
////                    initLineChart()
////                      setChart(listData,xLabelIngredients1)
////
////                }
////
////            }
////        )
////    }

 }
