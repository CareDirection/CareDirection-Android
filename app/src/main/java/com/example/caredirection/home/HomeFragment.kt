package com.example.caredirection.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.common.CustomDialogFragment
import com.example.caredirection.data.RvCareProductData
import com.example.caredirection.data.RvFunctionalSelectedData
import com.example.caredirection.home.functional.FunctionalSelectedFeatureAdapter
import com.example.caredirection.home.functional.HomeFunctionalActivity
import com.github.mikephil.charting.components.AxisBase
import com.orhanobut.dialogplus.DialogPlus
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.menu_top_home.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        val idx = rv_care_view.getChildAdapterPosition(v!!)
        //데이터가 담긴 배열의 idx 번째 데이터를 가져옴.
        Toast.makeText(context, idx.toString(), Toast.LENGTH_SHORT).show()


    }

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    val listData = ArrayList<BarEntry>()

    //private lateinit var
    private var rvCareProductData = listOf<RvCareProductData>()
    private lateinit var homeFragmentView :View
    private lateinit var rvCareProductAdapter: CareProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //bottom navigation 설정 시작 - in onCreate
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //bottom navigation 설정 시작 - in onCreate

        //recycler view 메모리로 가져오기
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeFragmentView= inflater.inflate(R.layout.fragment_home, container, false)

        return homeFragmentView}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerHomeEssentialArray = resources.getStringArray(R.array.spinner_home_essential_items)
        val spinnerHomeEssentialArrayAdapter=ArrayAdapter(context!!,R.layout.spinner_home_essential,spinnerHomeEssentialArray)

        //TODO 통신으로 받은 데이터 넘겨주기
        //그래프 그려주기
        listData.add(BarEntry(0f, 55f))
        listData.add(BarEntry(1f, 20f))
        listData.add(BarEntry(2f,60f))
        listData.add(BarEntry(3f,80f))
        listData.add(BarEntry(4f,120f))
        listData.add(BarEntry(5f,40f))
        listData.add(BarEntry(6f, 55f))
        listData.add(BarEntry(7f, 20f))
        listData.add(BarEntry(8f,60f))
        listData.add(BarEntry(9f,80f))
        listData.add(BarEntry(10f,120f))
        initLineChart()

        //밑에 라벨 //TODO 네이밍 다시 하기
        val vitamins = arrayOf("A1", "A2", "B", "C", "D", "E", "A3", "B1", "C2", "D3", "E4")


        //스피너 초기 설정
        homeFragmentView.spinner_home_essential.dropDownVerticalOffset = 4 //스피너 드롭다운 위치 설정
        homeFragmentView.spinner_home_essential.adapter = spinnerHomeEssentialArrayAdapter
        //스피너 초기값은 0 (즉, 낮은 순)
        homeFragmentView.spinner_home_essential.setSelection(0)

        homeFragmentView.spinner_home_essential.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //스피너 설정 값에 따라서 선택정렬해줌
                //그럼 그려주는 순서가 바뀐다
                when(position){
                    0->{
                        //낮은 순
                        for( i in 0 until listData.size-1){
                            var min = i
                            for(j in i+1 until listData.size){
                                if(listData[j].y <= listData[min].y){
                                    min = j
                                }
                            }
                            if( i != min){

                                //순서 선택정렬
                                var temp = listData[i].y
                                listData[i].y = listData[min].y
                                listData[min].y = temp

                                //매칭되어있는 라벨도 함께 이동
                                var tempX= vitamins[i]
                                vitamins[i] = vitamins[min]
                                vitamins[min] = tempX
                            }
                        }
                        setChart(listData, vitamins)

                    }
                    1->{
                        //높은 순
//                        listData.sortByDescending {
//                            it.y
//                        }

                        for( i in 0 until listData.size-1){
                            var min = i
                            for(j in i+1 until listData.size){
                                if(listData[j].y >= listData[min].y){
                                    min = j
                                }
                            }
                            if( i != min){
                                var temp = listData[i].y
                                listData[i].y = listData[min].y
                                listData[min].y = temp

                                var tempX= vitamins[i]
                                vitamins[i] = vitamins[min]
                                vitamins[min] = tempX
                            }
                        }

                        setChart(listData, vitamins)
                    }
                }
            }

        }





        essential_details.setOnClickListener{

            //프래그먼트 다이얼로그 생성
            val fm = fragmentManager!!
            val myfrag = CustomDialogFragment()
            myfrag.show(fm, "demo")
        }
        //기능성원료 - 케어받는 기능 정리
        rv_home_functional_selected_view.layoutManager=LinearLayoutManager(context)
        val rvHomeFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(context!!)
        rv_home_functional_selected_view.adapter = rvHomeFunctionalSelectedFeatureAdapter

        rvHomeFunctionalSelectedFeatureAdapter.data=listOf(
            RvFunctionalSelectedData(listOf("장건강","피로회복"),"오메가3"),
            RvFunctionalSelectedData(listOf("혈행개선"),""),
            RvFunctionalSelectedData(listOf("장건강","피로회복","눈건강"),"프로폴리스"),
            RvFunctionalSelectedData(listOf("피로회복","뼈","장건강"),"오메가3"),
            RvFunctionalSelectedData(listOf("운동보조","두뇌활동"),"홍삼")
        )


        //TOdo 리싸이클러뷰가 있으면 보이게 바꾸고 card 사라지게 만들기
        btn_home_care_product_register.setOnClickListener{
            rv_care_view.isVisible=true
            btn_home_care_product_register.visibility=GONE
        }


        //card-리사이클러뷰 가져오기

        //rvCareView.isVisible=true
        //리사이클러뷰 레이아웃 설정
        rv_care_view.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        //어댑더 정의
        rvCareProductAdapter=CareProductAdapter(context!!)
        //카드뷰에 어댑터 연결
        rv_care_view.adapter = rvCareProductAdapter
        rvCareProductAdapter.setOnClick(this)
        //더미 데이터 넣어주기
        rvCareProductAdapter.data = listOf(
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd")
        )

        //

        //
        homeFragmentView.btn_home_user_select.btn_change_user.setOnClickListener {
            //todo
            // topDownDialog=DialogPlus.newDialog(context)//빌더 설정
            // .setExpanded(true,100)
            // .setAdapter()


            val adapter = TopDownDialogHolder(context!!)
            //TODO: 서버에서 받아온 녀석으로 하셈.
            adapter.data = listOf(
                TopDownDialogHolder.Child("1", "엄마"),
                TopDownDialogHolder.Child("2", "은이"),
                TopDownDialogHolder.Child("3", "버미"),
                TopDownDialogHolder.Child("3", "명히")
            )
            DialogPlus.newDialog(context)
                .setGravity(Gravity.TOP)
                .setAdapter(adapter)
                .setExpanded(false)
                .setPadding(0,0,0,34)
                .setContentBackgroundResource(R.drawable.white_border_top_down)
                .create().show()
        }
        homeFragmentView.functional_details.setOnClickListener{
            val functional_intent = Intent(context,HomeFunctionalActivity::class.java)
            //TODO
            startActivity(functional_intent)
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    //bottom navigation 설정 시작
        fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
        }
        override fun onDetach() {
            super.onDetach()
            listener = null
        }
        interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            fun onFragmentInteraction(uri: Uri)
        }

        companion object {
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    //bottom navigation 설정 끝
    private fun initLineChart() {

        val xAxis = chart_home.xAxis
//        xAxis.setDrawLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        val rightYAxis = chart_home.axisRight
        rightYAxis.isEnabled=false
        rightYAxis.mAxisMinimum=0f
        rightYAxis.mAxisMaximum=120f
        /*
        rightYAxis.granularity= 40f // 라벨 간격 조정
        rightYAxis.setDrawLabels(false)
        rightYAxis.setDrawGridLines(false)
        rightYAxis.setDrawAxisLine(false)
        */
        val ll1 = LimitLine(100f, "Maximum Limit")
        ll1.lineWidth = 3f
        ll1.enableDashedLine(50f, 20f, 0f)
        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)
        //TODO phase는 무엇인가?
        ll1.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        ll1.lineColor = ContextCompat.getColor(context!!, android.R.color.black)

        //Todo 컬러가 안 먹음

        ll1.textSize = 10f
       // ll1.lineColor=R.color.colorGrey

        val ll2 = LimitLine(30f, "Minimum Limit")
        ll2.lineWidth = 3f//선의 굵기
     //   ll2.lineColor=R.color.colorPrimary //Todo
        val color=ll2.lineColor
        ll2.label=color.toString()
        ll2.enableDashedLine(50f, 20f, 0f)
        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)
        //TODO phase는 무엇인가?
        ll2.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
        ll2.textSize = 10f

        val leftYAxis = chart_home.axisLeft
        leftYAxis.setAxisMaximum(120f)
        leftYAxis.setAxisMinimum(0f)
        //leftYAxis.mAxisMinimum=0f
        //leftYAxis.mAxisMaximum=120f
        leftYAxis.granularity= 20f //라벨 써주는 간격 조정
        leftYAxis.setDrawLabels(true)
        leftYAxis.setDrawGridLines(false)
        leftYAxis.setDrawAxisLine(false)

        leftYAxis.addLimitLine(ll1)  // 상한선 그리기
        leftYAxis.addLimitLine(ll2)


    }

    private fun setChart(listData: ArrayList<BarEntry>, vitamins : Array<String>) {
        val dataSet = BarDataSet(listData, "")

        val listColor = ArrayList<Int>()
//        listColor.add(ContextCompat.getColor(context!!, R.color.colorBlueGraph))
//        listColor.add(ContextCompat.getColor(context!!, R.color.colorRedGraph))

        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                Log.v("YGYG", value.toInt().toString())
//                Log.v("YGYG", vitamins[value.toInt()])

                return vitamins[value.toInt()]

            }
        }

        listData.forEach {
            if(it.y >  100.0f || it.y < 30.0f)
                listColor.add(ContextCompat.getColor(context!!, R.color.colorRedGraph))
            else
                listColor.add(ContextCompat.getColor(context!!, R.color.colorBlueGraph))
        }

        dataSet.colors = listColor

        dataSet.valueTextColor = ContextCompat.getColor(context!!, android.R.color.black)

        dataSet.setDrawValues(false)

        chart_home.legend.isEnabled = false// 색 블럭 그룹핑할떄 이색은 이 그룹이다

        chart_home.description.isEnabled = false


        val lineData = BarData(dataSet)
        chart_home.data = lineData
        chart_home.setFitBars(true)
        chart_home.setTouchEnabled(true)//됨
        chart_home.isHorizontalScrollBarEnabled=true
        //chart.isDragXEnabled=true
        chart_home.isDragDecelerationEnabled=true
        // chart.setDragXEnabled(true)
        //chart.setHorizontalScrollBarEnabled(true)
        chart_home.isDoubleTapToZoomEnabled=true//두번터치하고 스크롤은 됨
        chart_home.isDragXEnabled=true
        chart_home.setVisibleXRange(3f,8f)
        chart_home.animateY(1000) //세로축 에니메이션
        // chart.data = lineData

        val xAxis = chart_home.xAxis
        xAxis.valueFormatter = formatter

        chart_home.invalidate()


    }
}

