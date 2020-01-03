package com.example.caredirection.product.standard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.ProductSearchContentData
import com.example.caredirection.data.network.ProductStandardData
import com.example.caredirection.data.network.Search
import com.example.caredirection.network.RequestURL
import com.example.caredirection.product.result.ProductSearchResult
import com.example.caredirection.product.detail.ActivityProductDetail
import kotlinx.android.synthetic.main.activity_product_standard.*
import kotlinx.android.synthetic.main.dialog_product_standard_explation.view.*
import kotlinx.android.synthetic.main.dialog_product_standard_filter.view.*
import kotlinx.android.synthetic.main.rv_item_product_standard.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ActivityProductStandard : AppCompatActivity() {

    private lateinit var rv_main_product: RecyclerView
    private lateinit var rv_main_product_adapter: MainProductAdapter

    private lateinit var dialog_absorption: String
    private lateinit var dialog_citation: String
    private lateinit var dialog_content: String

    private lateinit var extraname: String

    private lateinit var standardcontent_txt:String
    private lateinit var standardabsorption_txt: String
    private lateinit var standardcertification_txt: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_standard)

        initList()

        try{
            extraname = intent.getStringExtra("name")!!.toString()
            txt_activity_product_top_title.text = extraname
            getProductStandardItem(txt_activity_product_top_title.text.toString())

        }catch(e : Exception){
            Toast.makeText(this@ActivityProductStandard,"안돼", Toast.LENGTH_SHORT).show()
        }




        img_activity_product_top_filter.setOnClickListener {
            filterDialog()
        }




        img_activity_product_top_search.setOnClickListener {
            val intent = Intent(this@ActivityProductStandard, ProductSearchResult::class.java)

            startActivity(intent)
        }
    }

    private fun filterDialog(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_txt_test, null)

        //endregion

        builder.setView(dialogView)
            .setPositiveButton("OK") { dialogInterface, i ->

            } .show()

    }

    private fun initList() {
//1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
        rv_main_product_adapter = MainProductAdapter(this@ActivityProductStandard)

        //리사이클러뷰 가져오기
        rv_main_product = findViewById(R.id.activity_product_rv)

        //리사이클러뷰에 어뎁더 써서 연결하기
        rv_main_product.adapter = rv_main_product_adapter

        //리사이클러뷰에 리니얼로 넣기
        rv_main_product.layoutManager = LinearLayoutManager(this@ActivityProductStandard)



        getProductStandardItem(txt_activity_product_top_title.text.toString())
    }
    private fun dialogShow(){
        activity_product_detail_item_standard1.setOnClickListener {
            contentDialog(txt_activity_product_detail_standard1.text.toString() , standardcontent_txt)
        }
        activity_product_detail_item_standard2.setOnClickListener{
            contentDialog(txt_activity_product_detail_standard2_txt.text.toString() , standardabsorption_txt)
        }
        activity_product_detail_item_standard3.setOnClickListener{
            contentDialog(activity_product_cardview_standard3_txt.text.toString() , standardcertification_txt)
        }

    }


    private fun contentDialog(content_title : String, content_txt : String) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_explation, null)

        //dialogView.txt_dialog_product_standard_explain.tex

        ///여기 하기
        dialogView.txt_dialog_product_standard_content.text = content_title
        dialogView.txt_dialog_product_standard_explain.text = content_txt

        val dialog = builder.setView(dialogView).show()

        dialogView.txt_dialog_product_standard_close.setOnClickListener {
            dialog.dismiss()
        }
    }

   /* private fun filterDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_filter, null)

        //region 체크박스 흡수
        dialogView.checked_dialog_product_standard_absorption_left.setOnClickListener {
            dialogView.checked_dialog_product_standard_absorption_left.isChecked = true
            dialogView.checked_dialog_product_standard_absorption_right.isChecked = false
            dialog_absorption = "EE"
        }
        dialogView.checked_dialog_product_standard_absorption_right.setOnClickListener {
            dialogView.checked_dialog_product_standard_absorption_right.isChecked = true
            dialogView.checked_dialog_product_standard_absorption_left.isChecked = false
            dialog_absorption = "rtg"
        }
        //endregion 체크박스 흡수

        //region 인증마크
        dialogView.checked_dialog_product_standard_citation_left.setOnClickListener {
            dialogView.checked_dialog_product_standard_citation_left.isChecked = true
            dialogView.checked_dialog_product_standard_citation_right.isChecked = false
            dialog_citation = "O"
        }
        dialogView.checked_dialog_product_standard_citation_right.setOnClickListener {
            dialogView.checked_dialog_product_standard_citation_left.isChecked = false
            dialogView.checked_dialog_product_standard_citation_right.isChecked = true
            dialog_citation = "X"
        }
        //endregion 인증마크

        //region 함량
        val category = arrayOf("10000mg ~ 1000mg", "20000mg ~ 3000mg", "40000mg ~ 5000mg")
        val categoryAdapter = ArrayAdapter(
            this@ActivityProductStandard,
            R.layout.spinner_product_search_item,
            category
        )
        //categoryAdapter.setDropDownViewResource(R.layout.fragment_product_search)
        dialogView.spinner_dialog_product_standard.adapter = categoryAdapter

        dialogView.spinner_dialog_product_standard.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    dialog_content = category[position]
                }
            }
        //endregion

        builder.setView(dialogView)
            .setPositiveButton("확인") { dialogInterface, i ->
                activity_product_detail_standard1.text = dialog_content
                txt_activity_product_detail_standard2.text = dialog_absorption
                activity_product_cardview_standard3.text = dialog_citation
            }
            .setNegativeButton("취소") { dialogInterface, i ->
            }
            .show()
    }*/

    var check = mutableListOf<Boolean>()

    private fun getProductStandard(position: Int) {
        val call: Call<ProductStandardData> =
            RequestURL.service.getProductStandard(product_idx = position.toString())
        call.enqueue(
            object : Callback<ProductStandardData> {
                override fun onFailure(call: Call<ProductStandardData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductStandardData>,
                    response: Response<ProductStandardData>
                ) {
                    if(response.isSuccessful){
                        val ProductStandardnutrient: ProductStandardData = response.body()!!

                        standardcontent_txt = ProductStandardnutrient.data[0].standard_description
                        standardabsorption_txt = ProductStandardnutrient.data[1].standard_description
                        standardcertification_txt = ProductStandardnutrient.data[2].standard_description
                        dialogShow()
                        ProductStandardnutrient.data[0].standard_description
                        txt_activity_product_detail_standard1.text =
                            ProductStandardnutrient.data[0].standard
                        activity_product_detail_standard1.text =
                            ProductStandardnutrient.data[0].standard_value
                        txt_activity_product_detail_standard2.text =
                            ProductStandardnutrient.data[1].standard_value
                        txt_activity_product_detail_standard2_txt.text =
                            ProductStandardnutrient.data[1].standard
                        activity_product_cardview_standard3.text =
                            ProductStandardnutrient.data[2].standard_value
                        activity_product_cardview_standard3_txt.text =
                            ProductStandardnutrient.data[2].standard
                    }

                }
            })
    }

    //제품정보 가져오기
    private fun getProductStandardItem(search_word: String) {
        val call: Call<ProductSearchContentData> =
            RequestURL.service.getProductList(search_word, "product")
        call.enqueue(
            object : Callback<ProductSearchContentData> {
                override fun onFailure(call: Call<ProductSearchContentData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductSearchContentData>,
                    response: Response<ProductSearchContentData>
                ) {
                    if(response.isSuccessful){
                        val productSearchList: ProductSearchContentData = response.body()!!

                        //Toast.makeText(context,  productSearchList.data[0].tab_name,Toast.LENGTH_SHORT).show()
                        //어뎁터에 데이터 search객체 List로 추가
                        (0 until productSearchList.data.searchList.size!!).forEach {
                            rv_main_product_adapter.data.add(productSearchList.data.searchList[it])
                            rv_main_product_adapter.notifyDataSetChanged()
                            check.add(false)
                        }
                    }


                }
            })
    }

    inner class MainProductAdapter(private val context: Context) :
        RecyclerView.Adapter<MainProductAdapter.MainProductHolder>() {

        var data = mutableListOf<Search>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductHolder {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.rv_item_product_standard, parent, false)

            return MainProductHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: MainProductHolder, position: Int) {
            holder.bind(data[position], position)
        }

        inner class MainProductHolder(view: View) : RecyclerView.ViewHolder(view) {

            val img_rv_search_result_item_product: ImageView = view.findViewById(R.id.img_rv_search_result_item_product)
            //해외직구를 체크로 해야하니까 publisher를 나중에 바꾸기
            val txt_rv_item_product_ename: TextView =
                view.findViewById(R.id.txt_rv_search_result_item_ename)
            /* val txt_rv_item_product_publisher: TextView =
                 view.findViewById(R.id.txt_rv_search_result_item_publisher)*/
            val txt_rv_item_product_kname: TextView =
                view.findViewById(R.id.txt_rv_search_result_item_kname)
            val txt_rv_item_product_price: TextView =
                view.findViewById(R.id.txt_rv_search_result_item_price)
            val txt_rv_search_result_item_perstandard: TextView =
                view.findViewById(R.id.txt_rv_search_result_item_perstandard)
            val txt_rv_search_result_item_perprice: TextView =
                view.findViewById(R.id.txt_rv_search_result_item_perprice)
            val txt_rv_search_result_item_publisher: TextView   = view.findViewById(R.id.txt_rv_search_result_item_publisher)
            val rv_item_product_indicator: CheckedTextView =
                view.findViewById(R.id.rv_standard_item_product_indicator)
            val img_rv_item_standard: CheckedTextView =
                view.findViewById(R.id.img_rv_standard_item_filter)

            fun bind(item: Search,  position: Int) {
                val product_idx: Int = item.product_idx
                //여기서 제품 받아와야대
                //val product_quantity_count: Int = item.product_quantity_count

                Glide.with(this@ActivityProductStandard)
                    .load(item.image_key)
                    .centerCrop()
                    .into(img_rv_search_result_item_product)

                txt_rv_item_product_ename.text = item.product_company_name
               // txt_rv_item_product_publisher.text = item.Publisher
                txt_rv_item_product_kname.text = item.product_name
                txt_rv_item_product_price.text = item.product_quantity_price.toString()
                rv_item_product_indicator.isChecked = check[position]
                img_rv_item_standard.isChecked = check[position]
                txt_rv_search_result_item_perstandard.text =item.product_quantity_count.toString()
                if(item.product_quantity_count != 0){
                    txt_rv_search_result_item_perprice.text =
                        (item.product_quantity_price / item.product_quantity_count).toString()
                }
                if(item.product_is_import == 0){
                    txt_rv_search_result_item_publisher.visibility = View.INVISIBLE
                }
                else if(item.product_is_import == 1){
                    txt_rv_search_result_item_publisher.visibility = View.VISIBLE
                }
                itemView.setOnClickListener {
                    val intent = Intent(this@ActivityProductStandard, ActivityProductDetail::class.java)

                    intent.putExtra("name", item.product_idx.toString())

                    startActivity(intent, null)
                }
                img_rv_item_standard.setOnClickListener {
                    (0 until check.size).forEach {
                        check[it] = false
                    }
                    check[position] = true
                    getProductStandard(item.product_idx)
                    dialogShow()
                    notifyDataSetChanged()
                }
            }
        }

    }



}
