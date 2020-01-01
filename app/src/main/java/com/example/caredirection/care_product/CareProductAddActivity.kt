package com.example.caredirection.care_product

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caredirection.R
import com.example.caredirection.research.userInfo.NumberPicker
import kotlinx.android.synthetic.main.activity_care_product_add.*

class CareProductAddActivity : AppCompatActivity() {

    private lateinit var product_add_medicine: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_product_add)

        makeController()
    }

    private fun makeController(){
        val medicine = arrayOf("1정","2정","3정","4정","5정","6정","7정","8정","9정","10정")
        val medicineAdapter = ArrayAdapter(
            this,
            R.layout.spinner_product_search_item,
            medicine
        )
        //categoryAdapter.setDropDownViewResource(R.layout.fragment_product_search)
        sp_product_add.adapter = medicineAdapter

        sp_product_add.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //함량 변수에 저장
                    product_add_medicine = medicine[position].toString()
                }
            }

        txt_year_picker.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_date_picker, null)

            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    val year_picker = dialogView.findViewById<NumberPicker>(R.id.year_picker)
                    val month_picker = dialogView.findViewById<NumberPicker>(R.id.month_picker)
                    val date_picker = dialogView.findViewById<NumberPicker>(R.id.date_picker)
                    txt_year_picker?.text = year_picker.value.toString()+"년 " + month_picker.value.toString()+ "월 " + date_picker.value.toString()+"일 "
                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    txt_year_picker?.text = ""
                }
                .show()
        }

        txt_time_picker.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_time_picker, null)

            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    val hour_picker = dialogView.findViewById<NumberPicker>(R.id.hour_picker)
                    val minute_picker = dialogView.findViewById<NumberPicker>(R.id.minute_picker)
                    txt_time_picker?.text = hour_picker.value.toString()+"시 " + minute_picker.value.toString()+ "분 "
                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    txt_time_picker?.text = ""
                }
                .show()
        }
    }
}
