package com.example.caredirection.home.functional

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.data.RvFunctionalSelectedData
import kotlinx.android.synthetic.main.fragment_functional.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class FunctionalFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_functional, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //탑 바
        txt_top_bar_home_functional.txt_top_bar_title.text="기능성 원료"

        //케어받는 기능
        rv_functional_selected_view.layoutManager= LinearLayoutManager(context)
        val rvFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(context!!)
        rv_functional_selected_view.adapter=rvFunctionalSelectedFeatureAdapter
        rvFunctionalSelectedFeatureAdapter.data=listOf(
            RvFunctionalSelectedData(listOf("장건강","피로회복"),"오메가3"),
            RvFunctionalSelectedData(listOf("혈행개선"),""),
            RvFunctionalSelectedData(listOf("장건강","피로회복","눈건강"),"프로폴리스"),
            RvFunctionalSelectedData(listOf("피로회복","뼈","장건강"),"오메가3"),
            RvFunctionalSelectedData(listOf("운동보조","두뇌활동"),"홍삼")
        )


        //전체기능리사이클러뷰 레이아웃 설정
        rv_functional_all_view.layoutManager = GridLayoutManager(context,3)
        //어댑더 정의
        val rvFunctionalAllAdapter = FunctionalAllFeatureAdapter(context!!)
        //뷰에 어댑터 연결
        rv_functional_all_view.adapter = rvFunctionalAllAdapter
        //더미 데이터 넣어주기
        rvFunctionalAllAdapter.data = listOf(
            RvFunctionalAllData(R.drawable.btn_liver,"간겅강"),
            RvFunctionalAllData(R.drawable.btn_fatigue_recovery,"피로회복"),
            RvFunctionalAllData(R.drawable.btn_eye,"눈건강"),
            RvFunctionalAllData(R.drawable.btn_blood,"혈행개선"),
            RvFunctionalAllData(R.drawable.btn_immunity,"면역력 활성화"),
            RvFunctionalAllData(R.drawable.btn_digestive,"소화기능"),
            RvFunctionalAllData(R.drawable.btn_brain,"두뇌활동"),
            RvFunctionalAllData(R.drawable.btn_muscle,"운동보조"),
            RvFunctionalAllData(R.drawable.btn_bone,"뼈")
        )


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FunctonalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FunctionalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
