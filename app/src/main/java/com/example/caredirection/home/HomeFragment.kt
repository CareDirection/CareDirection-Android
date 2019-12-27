package com.example.caredirection.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductData
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
    private lateinit var homeFragmentView :View
    private lateinit var rvCareView : RecyclerView
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

        //스피너 설정
        val spinnerHomeEssentialArray = resources.getStringArray(R.array.spinner_home_essential_items)
        val spinnerHomeEssentialArrayAdapter=ArrayAdapter(context!!,R.layout.spinner_home_essential,spinnerHomeEssentialArray)

        homeFragmentView.spinner_home_essential.adapter = spinnerHomeEssentialArrayAdapter
        homeFragmentView.spinner_home_essential.dropDownVerticalOffset = 4 //스피너 드롭다운 위치 설정
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
                //TODO 내부 코드 완성하기
                    when(position){
                        0->{

                        }
                        1->{

                        }
                    }
            }

        }
        return homeFragmentView}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //리사이클러뷰 가져오기
        rvCareView = homeFragmentView.findViewById(R.id.rv_care_view) as RecyclerView
        //리사이클러뷰 레이아웃 설정
        rvCareView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        //어댑더 정의
        rvCareProductAdapter=CareProductAdapter(context!!)
        //카드뷰에 어댑터 연결
        rvCareView.adapter = rvCareProductAdapter

        //더미 데이터 넣어주기
        rvCareProductAdapter.data = listOf(
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd"),
            RvCareProductData(R.color.colorRed,true,"ddddddddd")
        )
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

