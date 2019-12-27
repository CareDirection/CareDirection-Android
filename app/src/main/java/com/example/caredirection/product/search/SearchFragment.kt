package com.example.caredirection.product.search

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.standard.main_product_rv_item
import kotlinx.android.synthetic.main.fragment_product_search.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rv_search_nutrient: RecyclerView
    private lateinit var rv_search_nutirient_adapter : SearchNutrientAdapter

    private lateinit var rv_search_product: RecyclerView
    private lateinit var rv_search_product_adapter : SearchProductAdapter
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
        val view = inflater.inflate(R.layout.fragment_product_search, container, false)

        val category = arrayOf("제품","성분")
        val categoryAdapter = ArrayAdapter(context!!,R.layout.spinner_product_search_item, category)
        //categoryAdapter.setDropDownViewResource(R.layout.fragment_product_search)
        view.spinner_fragment_product_search.adapter = categoryAdapter

        view.spinner_fragment_product_search.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                        Toast.makeText(context!!, "1번", Toast.LENGTH_LONG)
                    }
                    1 -> {
                        Toast.makeText(context!!, "1번", Toast.LENGTH_LONG)
                    }
                }
            }
        }

        //1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
        rv_search_nutirient_adapter = SearchNutrientAdapter(context!!)
        rv_search_nutrient = view.findViewById(R.id.rv_search_nutrient_txt)//

        rv_search_nutrient.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        rv_search_nutirient_adapter.data = listOf(
          search_rv_nutrient_item("오메가3"),
            search_rv_nutrient_item("종합비타민"),
            search_rv_nutrient_item("홍삼"),
            search_rv_nutrient_item("로얄젤리"),
            search_rv_nutrient_item("비타민A"),
            search_rv_nutrient_item("비타민D")
        )
        //리사이클러뷰에 어뎁더 써서 연결하기
        rv_search_nutrient.adapter = rv_search_nutirient_adapter


        rv_search_product_adapter = SearchProductAdapter(context!!)
        rv_search_product = view.findViewById(R.id.rv_search_item_product)

        rv_search_product.layoutManager = LinearLayoutManager(requireContext())
        rv_search_product_adapter.data = listOf(
            rv_search_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            rv_search_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            rv_search_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            rv_search_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            rv_search_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            rv_search_item("ENGliSH NAME","publisher","KOREA NAME","price")
        )
        rv_search_product.adapter = rv_search_product_adapter

        return view
    }

  /*  override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/

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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
