package com.example.caredirection.product.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.result.ProductSearchResult
import com.example.caredirection.product.standard.ActivityProductStandard
import kotlinx.android.synthetic.main.fragment_product_search.view.*

class SearchFragment : Fragment() {

    private lateinit var rv_search_nutrient: RecyclerView
    private lateinit var rv_search_nutirient_adapter : SearchNutrientAdapter

    private lateinit var txt_search_fragment_intent: TextView
    private lateinit var rv_search_product: RecyclerView
    private lateinit var rv_search_product_adapter : SearchProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_search, container, false)

        //스피너
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
                   /* 0 -> {
                        Toast.makeText(context!!, "1번", Toast.LENGTH_LONG).show()
                    }
                    1 -> {
                        Toast.makeText(context!!, "1번", Toast.LENGTH_LONG).show()
                    }*/
                }
            }
        }

        //1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
        rv_search_nutirient_adapter = SearchNutrientAdapter()
        rv_search_nutrient = view.findViewById(R.id.rv_search_nutrient_txt)//

        rv_search_nutrient.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        data = mutableListOf(
            SearchNutrientItem("오메가3", true),
            SearchNutrientItem("종합비타민"),
            SearchNutrientItem("홍삼"),
            SearchNutrientItem("로얄젤리"),
            SearchNutrientItem("비타민A"),
            SearchNutrientItem("비타민D")
        )
        //리사이클러뷰에 어뎁더 써서 연결하기
        rv_search_nutrient.adapter = rv_search_nutirient_adapter

        rv_search_product_adapter = SearchProductAdapter(context!!)
        rv_search_product = view.findViewById(R.id.rv_search_item_product)

        rv_search_product.layoutManager = LinearLayoutManager(requireContext())
        rv_search_product_adapter.item = listOf(
            SearchProductAdapter.rv_search_item("ENGliSH NAME", "publisher", "KOREA NAME", "price", false),
            SearchProductAdapter.rv_search_item("ENGliSH NAME", "publisher", "KOREA NAME", "price", false)
        )
        rv_search_product.adapter = rv_search_product_adapter

        //
        txt_search_fragment_intent = view.findViewById(R.id.txt_search_fragment_intent)
        txt_search_fragment_intent.setOnClickListener{
            val intent = Intent(context, ActivityProductStandard::class.java)

            startActivityForResult(intent, 100)
        }

        view.txt_search_fragment.setOnClickListener{
            val intent = Intent(context, ProductSearchResult::class.java)

            startActivity(intent)
        }

        return view
    }

    //region Nutrient RecyclerView

    private var data = mutableListOf<SearchNutrientItem>()

    private inner class SearchNutrientAdapter: RecyclerView.Adapter<SearchNutrientHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNutrientHolder {
            return SearchNutrientHolder(layoutInflater.inflate(R.layout.rv_item_search_nutrient, parent, false))
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: SearchNutrientHolder, position: Int) {
            holder.bind(position)
        }
    }

    private inner class SearchNutrientHolder(view: View): RecyclerView.ViewHolder(view) {
        val txt_search_nutrient : TextView = view.findViewById(R.id.txt_rv_search_nutrient)
        val selector_rv_item_nutrient: CheckedTextView = view.findViewById(R.id.selector_rv_item_nutrient)
        val txt_rv_search_nutrient : CheckedTextView = view.findViewById(R.id.txt_rv_search_nutrient)

        fun bind(position: Int){
                val item = data[position]
                txt_search_nutrient.text = item.nutrient
                selector_rv_item_nutrient.isChecked = item.check
                txt_rv_search_nutrient.isChecked = item.check

                itemView.setOnClickListener{
                    (0 until data.size).forEach {
                        data[it] = data[it].copy(check = false)
                    }
                    data[position] = data[position].copy(check = true)
                    rv_search_nutirient_adapter.notifyDataSetChanged()
                }
        }
    }

    data class SearchNutrientItem(
        val nutrient : String,
        val check: Boolean = false
    )

    //endregion RecyclerView
}
