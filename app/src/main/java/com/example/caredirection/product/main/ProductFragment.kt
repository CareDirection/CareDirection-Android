package com.example.caredirection.product.main
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import kotlinx.android.synthetic.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductFragment : Fragment(){

        private var param1: String? = null
        private var param2: String? = null
        private var listener: OnFragmentInteractionListener? = null

        private lateinit var rv_main_product: RecyclerView
        private lateinit var rv_main_product_adapter:MainProductAdapter

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
            val view = inflater.inflate(R.layout.fragment_product, container, false)


            initList(view)
            return view
        }

        private fun initList(view: View){
            //1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
            rv_main_product_adapter = MainProductAdapter(context!!)
            //리사이클러뷰 가져오기
            rv_main_product = view.findViewById(R.id.fragment_product_rv)
            //리사이클러뷰에 리니얼로 넣기
            rv_main_product.layoutManager = LinearLayoutManager(requireContext())
            rv_main_product_adapter.data = listOf(
                main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
                main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
                main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
                main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
                main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
                main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price")
            )

            //리사이클러뷰 가져오기

            //리사이클러뷰에 어뎁더 써서 연결하기
            rv_main_product.adapter = rv_main_product_adapter

        }

        // TODO: Rename method, update argument and hook method into UI event
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
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment home.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                ProductFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }

