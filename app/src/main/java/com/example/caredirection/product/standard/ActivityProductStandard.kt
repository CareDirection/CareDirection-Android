package com.example.caredirection.product.standard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.detail.ActivityProductDetail
import kotlinx.android.synthetic.main.activity_product_standard.*
import kotlinx.android.synthetic.main.dialog_product_standard_explation.view.*

class ActivityProductStandard : AppCompatActivity() {

    private lateinit var rv_main_product: RecyclerView
    private lateinit var rv_main_product_adapter : MainProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_standard)

        initList()
        activity_product_detail_item_standard1.setOnClickListener{
            contentDialog()
        }
        img_activity_product_top_filter.setOnClickListener{
            filterDialog()
        }
        /*rv_main_product.setOnClickListener{

        }*/
    }
    private fun initList(){
//1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
        rv_main_product_adapter = MainProductAdapter(this@ActivityProductStandard)
        //리사이클러뷰 가져오기
        rv_main_product = findViewById(R.id.activityt_product_rv)
        //리사이클러뷰에 리니얼로 넣기
        rv_main_product.layoutManager = LinearLayoutManager(this@ActivityProductStandard)
        rv_main_product_adapter.data = mutableListOf(
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
    private fun contentDialog(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_explation, null)


        val dialog = builder.setView(dialogView).show()

        dialogView.txt_dialog_product_standard_close.setOnClickListener{
            dialog.dismiss()
        }
    }
    private fun filterDialog(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_filter, null)

        builder.setView(dialogView)
            .setPositiveButton("확인") { dialogInterface, i ->
            }
            .setNegativeButton("취소") { dialogInterface, i ->
            }
            .show()
    }

    inner class MainProductAdapter(private val context: Context): RecyclerView.Adapter<MainProductAdapter.MainProductHolder>() {
        var data = mutableListOf<main_product_rv_item>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_standard,parent,false)

            return MainProductHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: MainProductHolder, position: Int) {
            holder.bind(data[position], position)
        }

        inner class MainProductHolder(view: View): RecyclerView.ViewHolder(view) {
            var item = listOf<MainProductAdapter>()

            val txt_rv_item_product_ename : TextView = view.findViewById(R.id.txt_rv_standard_item_product_ename)
            val txt_rv_item_product_publisher: TextView = view.findViewById(R.id.txt_rv_standard_item_product_publisher)
            val txt_rv_item_product_kname: TextView = view.findViewById(R.id.txt_rv_standard_item_product_kname)
            val txt_rv_item_product_price : TextView = view.findViewById(R.id.txt_rv_standard_item_product_price)

            val rv_item_product_indicator: CheckedTextView = view.findViewById(R.id.rv_standard_item_product_indicator)
            val img_rv_item_standard: CheckedTextView = view.findViewById(R.id.img_rv_standard_item_filter)

            fun bind(item: main_product_rv_item, position: Int){
                txt_rv_item_product_ename.text = item.EndName
                txt_rv_item_product_publisher.text = item.Publisher
                txt_rv_item_product_kname.text = item.KorName
                txt_rv_item_product_price.text = item.Price
                rv_item_product_indicator.isChecked = item.Check
                img_rv_item_standard.isChecked = item.Check

                itemView.setOnClickListener {
                    startActivity(Intent(context, ActivityProductDetail::class.java))
                }

                img_rv_item_standard.setOnClickListener{
                    (0 until data.size).forEach {
                        data[it] = data[it].copy(Check = false)
                    }
                    data[position] = data[position].copy(Check = true)
                    notifyDataSetChanged()
                }
            }
        }

    }

    data class main_product_rv_item(
        val EndName: String,
        val Publisher: String,
        val KorName: String,
        val Price: String,
        val Check: Boolean = false
    )
}
