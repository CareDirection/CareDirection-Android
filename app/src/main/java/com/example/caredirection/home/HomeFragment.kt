package com.example.caredirection.home

import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.common.CustomDialogFragment
import com.example.caredirection.data.RvCareProductData
import com.example.caredirection.data.RvFunctionalSelectedData
import com.example.caredirection.home.care_product.CareProductAdapter
import com.example.caredirection.home.functional.FunctionalFragment
import com.example.caredirection.home.functional.FunctionalSelectedFeatureAdapter
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

        //position
        val idx = rv_care_view.getChildAdapterPosition(v!!)
        //데이터가 담긴 배열의 idx 번째 데이터를 가져옴.
        Toast.makeText(context, idx.toString(), Toast.LENGTH_SHORT).show()
        val fm = fragmentManager!!
        val myfrag = CustomDialogFragment()
        myfrag.show(fm, "demo")
        //rvCareProductAdapter.data[idx]
    }

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    val listData = ArrayList<BarEntry>()
    val listData2 = ArrayList<BarEntry>()

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

        listData2.add(BarEntry(0f, 55f))
        listData2.add(BarEntry(1f, 20f))
        listData2.add(BarEntry(2f,60f))
        listData2.add(BarEntry(3f,80f))
        listData2.add(BarEntry(4f,120f))
        listData2.add(BarEntry(5f,40f))
        listData2.add(BarEntry(6f, 55f))
        listData2.add(BarEntry(7f, 20f))
        listData2.add(BarEntry(8f,60f))
        listData2.add(BarEntry(9f,80f))
        listData2.add(BarEntry(10f,120f))
        initLineChart()

        //밑에 라벨 //TODO 네이밍 다시 하기
        val xLabelIngredients = arrayOf("비타민 A", "비타민", "B", "C", "D", "E", "A3", "B1", "C2", "D3", "E4")
        setChart(listData, xLabelIngredients)

        //스피너 초기 설정
        homeFragmentView.spinner_home_essential.dropDownVerticalOffset = 4 //스피너 드롭다운 위치 설정
        homeFragmentView.spinner_home_essential.adapter = spinnerHomeEssentialArrayAdapter
        //스피너 초기값은 0 (즉, 낮은 순)
        //homeFragmentView.spinner_home_essential.setSelection(0)
        //homeFragmentView.spinner_home_essential.isSelected=false
        homeFragmentView.spinner_home_essential.setSelection(0)
//        homeFragmentView.spinner_home_essential.setSelection(0,false)
        //homeFragmentView.spinner_home_essential.isSelected=true

        homeFragmentView.spinner_home_essential.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
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
                when(position){
                    0->{
                        setChart(listData, xLabelIngredients)
                    }
                    1->{

                        //낮은 순
                        for( i in 0 until listData2.size-1){
                            var min = i
                            for(j in i+1 until listData2.size){
                                if(listData2[j].y <= listData2[min].y){
                                    min = j
                                }
                            }
                            if( i != min){

                                //순서 선택정렬
                                var temp = listData2[i].y
                                listData2[i].y = listData2[min].y
                                listData2[min].y = temp

                                //매칭되어있는 라벨도 함께 이동
                                var tempX= xLabelIngredients[i]
                                xLabelIngredients[i] = xLabelIngredients[min]
                                xLabelIngredients[min] = tempX
                            }
                        }
                        setChart(listData2, xLabelIngredients)

                    }
                    2->{
                        //높은 순
//                        listData.sortByDescending {
//                            it.y
//                        }

                        for( i in 0 until listData2.size-1){
                            //선택정렬 높은순으로 합니다
                            var min = i
                            for(j in i+1 until listData2.size){
                                if(listData2[j].y >= listData2[min].y){
                                    min = j
                                }
                            }

                            if( i != min){
                                var temp = listData2[i].y
                                listData2[i].y = listData2[min].y
                                listData2[min].y = temp

                                //라벨도 같이 움직입니다.
                                var tempX= xLabelIngredients[i]
                                xLabelIngredients[i] = xLabelIngredients[min]
                                xLabelIngredients[min] = tempX
                            }
                        }

                        setChart(listData2, xLabelIngredients)
                    }

                }
            }

        }
//        setChart(listData, xLabelIngredients)

        essential_details.setOnClickListener{
            //필수 비타민 & 미네랄 상세보기로 이동
            //todo 커스텀 다이얼로그 띄워주는 위치 변경
            //프래그먼트 다이얼로그 생성
            val fm = fragmentManager!!
            val myfrag = CustomDialogFragment()
            myfrag.show(fm, "demo")
        }


        //기능성원료 - 케어받는 기능 정리
        rv_home_functional_selected_view.layoutManager=LinearLayoutManager(context)
        val rvHomeFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(context!!)
        rv_home_functional_selected_view.adapter = rvHomeFunctionalSelectedFeatureAdapter
        //TODO 통신 데이터 연결
        rvHomeFunctionalSelectedFeatureAdapter.data=listOf(
            RvFunctionalSelectedData(listOf("장건강","피로회복"),"오메가3"),
            RvFunctionalSelectedData(listOf("혈행개선"),""),
            RvFunctionalSelectedData(listOf("장건강","피로회복","눈건강"),"프로폴리스"),
            RvFunctionalSelectedData(listOf("피로회복","뼈","장건강"),"오메가3"),
            RvFunctionalSelectedData(listOf("운동보조","두뇌활동"),"홍삼")
        )


        //TOdo 리싸이클러뷰가 있으면 보이게 바꾸고 card 사라지게 만들기
        //이건 뭔가 만들었는데 실은 복용 등록 페이지로 이동후에
        //리싸이클러뷰를 띄워줌 -> 즉 리싸이클러뷰가 있으면 카드뷰 안띄워주는 거죠?
        btn_home_care_product_register.setOnClickListener{
            rv_care_view.isVisible=true
            btn_home_care_product_register.visibility=GONE
        }


        //card-리사이클러뷰 가져오기
        //rvCareView.isVisible=true
        //리사이클러뷰 레이아웃 설정
        rv_care_view.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        //어댑더 정의
        rvCareProductAdapter=
            CareProductAdapter(context!!)

        //클릭 리스너 받기
        rvCareProductAdapter.setOnClick(this)

        //카드뷰에 어댑터 연결
        rv_care_view.adapter = rvCareProductAdapter



        //TODO 통신 오면 고치기
        //더미 데이터 넣어주기
        rvCareProductAdapter.data = listOf(
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd")
        )

        //

        //이거는 current user 설정하는 것
        //TODO 커스텀 수정 / 클릭 이벤트 처리 등등
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
            //TODO 프래그먼트로 이동
            // val functional_intent = Intent(context,FunctonalFragment::class.java)
            //startActivity(functional_intent)
         activity!!.supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
             FunctionalFragment()
         ).addToBackStack(null).commit()
            }
    }



    //bottom navigation 설정 시작
        fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
        }
        override fun onDetach() {
            super.onDetach()
            listener = null
        }
        interface OnFragmentInteractionListener {
            fun onFragmentInteraction(uri: Uri)
        }
        companion object {
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
//        xAxis.setDrawLabels(false) 이것이 바로 라벨을 지워주는 친구였다 ex) 비타민 D 같은 것들
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f // 라벨 써주는 간격 조정
       // xAxis.mLabelRotatedWidth=10//이건 뭐지
      //이거하면 죽음  xAxis.spaceMax=5f

        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        val rightYAxis = chart_home.axisRight
        rightYAxis.isEnabled=false
        //rightYAxis.mAxisMinimum=0f
        //rightYAxis.mAxisMaximum=120f
        /*
        rightYAxis.granularity= 40f // 라벨 간격 조정
        rightYAxis.setDrawLabels(false)
        rightYAxis.setDrawGridLines(false)
        rightYAxis.setDrawAxisLine(false)
        */
        //y축
        val leftYAxis = chart_home.axisLeft
        leftYAxis.setAxisMaximum(120f)
        leftYAxis.setAxisMinimum(0f)
        //leftYAxis.mAxisMinimum=0f
        //leftYAxis.mAxisMaximum=120f
        leftYAxis.granularity= 20f //라벨 써주는 간격 조정
        leftYAxis.setDrawLabels(false) //todo 기-디한테 라벨 써주는지 아닌지 물어보기
        leftYAxis.setDrawGridLines(false)
        leftYAxis.setDrawAxisLine(false)

        //상한선 그려주기
        val ll1 = LimitLine(100f, "상한 섭취량")
        ll1.lineWidth = 3f
        ll1.enableDashedLine(50f, 20f, 0f)
        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)
        ll1.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
        //ll1.lineColor=R.color.colorRed 이렇게 하니까 안먹음 getcolor 사용해야함
        ll1.lineColor = ContextCompat.getColor(context!!,R.color.colorRed)

        ll1.textSize = 10f
       // ll1.lineColor=R.color.colorGrey

        val ll2 = LimitLine(30f, "권장 섭취량")
        ll2.lineWidth = 3f//선의 굵기
        //ll2.lineColor=R.color.colorPrimary //Todo
        ll2.lineColor = ContextCompat.getColor(context!!,R.color.colorPrimary)
        ll2.enableDashedLine(50f, 20f, 0f)
        //ll1.enableDashedLine(선의 길이, 선사이의 공간, 0f)
        //TODO phase는 무엇인가?
        ll2.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
        ll2.textSize = 10f



        leftYAxis.addLimitLine(ll1)  // 상한선 그리기
        leftYAxis.addLimitLine(ll2)


    }

    private fun setChart(listData: ArrayList<BarEntry>, xLabelIngredients : Array<String>) {
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
        chart_home.setVisibleXRange(3f,6f) // X에 그려줄 최소, 최대 단위 정하기
        chart_home.animateY(1000) //세로축 에니메이션
        // chart.data = lineData

        val xAxis = chart_home.xAxis
        xAxis.valueFormatter = formatter

        chart_home.invalidate()


    }
}

