package com.example.caredirection.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.common.CustomDialogFragment
import com.example.caredirection.data.RvCareProductData
import com.example.caredirection.data.RvFunctionalSelectedData
import com.example.caredirection.home.functional.FunctionalSelectedFeatureAdapter
import com.example.caredirection.home.functional.HomeFunctionalActivity
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.DialogPlusBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    //private lateinit var
    private var rvCareProductData = listOf<RvCareProductData>()
    private lateinit var homeFragmentView: View
    private lateinit var rvCareView: RecyclerView
    private lateinit var rvCareProductAdapter: CareProductAdapter
    private lateinit var topDownDialog: DialogPlusBuilder


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
        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)

        //스피너 설정
        val spinnerHomeEssentialArray =
            resources.getStringArray(R.array.spinner_home_essential_items)
        val spinnerHomeEssentialArrayAdapter =
            ArrayAdapter(context!!, R.layout.spinner_home_essential, spinnerHomeEssentialArray)

        homeFragmentView.spinner_home_essential.adapter = spinnerHomeEssentialArrayAdapter
        homeFragmentView.spinner_home_essential.dropDownVerticalOffset = 4 //스피너 드롭다운 위치 설정
        homeFragmentView.spinner_home_essential.setSelection(0)
        homeFragmentView.spinner_home_essential.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //TODO 내부 코드 완성하기
                    when (position) {
                        0 -> {

                        }
                        1 -> {

                        }
                    }
                }

            }
        return homeFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_home_functional_selected_view.layoutManager=LinearLayoutManager(context)
        val rvHomeFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(context!!)
        rv_home_functional_selected_view.adapter = rvHomeFunctionalSelectedFeatureAdapter

        rvHomeFunctionalSelectedFeatureAdapter.data=listOf(
            RvFunctionalSelectedData(listOf("장건강","피로회복"),"오메가3"),
            RvFunctionalSelectedData(listOf("혈행개선"),""),
            RvFunctionalSelectedData(listOf("장건강","피로회복","눈건강"),"오메가3"),
            RvFunctionalSelectedData(listOf("피로회복","뼈"),"오메가3"),
            RvFunctionalSelectedData(listOf("운동보조","두뇌활동"),"홍삼")
        )





        //card-리사이클러뷰 가져오기
        rvCareView = homeFragmentView.findViewById(R.id.rv_care_view) as RecyclerView
        //리사이클러뷰 레이아웃 설정
        rvCareView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //어댑더 정의
        rvCareProductAdapter = CareProductAdapter(context!!)
        //카드뷰에 어댑터 연결
        rvCareView.adapter = rvCareProductAdapter

        //더미 데이터 넣어주기
        rvCareProductAdapter.data = listOf(
            RvCareProductData(R.color.colorRed, true, "ddddddddd"),
            RvCareProductData(R.color.colorRed, true, "ddddddddd"),
            RvCareProductData(R.color.colorRed, true, "ddddddddd"),
            RvCareProductData(R.color.colorRed, true, "ddddddddd")
        )

        //프래그먼트 다이얼로그 생성
        val fm = fragmentManager!!
        val myfrag = CustomDialogFragment()
        myfrag.show(fm, "demo")
        //

        //
        homeFragmentView.btn_home_user_select.setOnClickListener {
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
}

