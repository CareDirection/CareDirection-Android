package com.example.caredirection.care_product

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.data.network.CareProductDialogData
import com.example.caredirection.data.network.RegisterData
import com.example.caredirection.network.RequestURL
import com.example.caredirection.research.userInfo.NumberPicker
import kotlinx.android.synthetic.main.activity_care_product_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CareProductAddActivity : AppCompatActivity() {

    private var product_add_medicine: Int = 0
    private lateinit var product_add_date: String
    private var product_add_time: String = "0000-00-00 "
    private var idx: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_product_add)

        idx = intent.getIntExtra("index", 1)
        idx.toString().logDebug()

        getCareProductDialog(idx)
        makeController()
    }

    private fun makeController() {
        //val medicine = arrayOf("1정","2정","3정","4정","5정","6정","7정","8정","9정","10정")
        val medicine = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
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
                    product_add_medicine = medicine[position]
                    product_add_medicine.toString().logDebug()
                }
            }

        txt_year_picker.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_date_picker, null)

            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    val year_picker = dialogView.findViewById<NumberPicker>(R.id.year_picker)
                    val month_picker = dialogView.findViewById<NumberPicker>(R.id.month_picker)
                    val date_picker = dialogView.findViewById<NumberPicker>(R.id.date_picker)
                    txt_year_picker?.text =
                        year_picker.value.toString() + "년 " + month_picker.value.toString() + "월 " + date_picker.value.toString() + "일 "

                    product_add_date = year_picker.value.toString() + "-"
                    if(month_picker.value<10){
                        product_add_date += "0"+month_picker.value.toString()+"-"
                    }
                    else{
                        product_add_date += month_picker.value.toString()+"-"
                    }
                    if(date_picker.value<10){
                        product_add_date += "0"+date_picker.value.toString()
                    }
                    else{
                        product_add_date += month_picker.value.toString()
                    }
                    product_add_date.logDebug()
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    txt_year_picker?.text = ""
                }
                .show()
        }

        txt_time_picker.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_time_picker, null)

            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    val hour_picker = dialogView.findViewById<NumberPicker>(R.id.hour_picker)
                    val minute_picker = dialogView.findViewById<NumberPicker>(R.id.minute_picker)
                    txt_time_picker?.text =
                        hour_picker.value.toString() + "시 " + minute_picker.value.toString() + "분"
                    product_add_time =
                        product_add_time + hour_picker.value.toString() + ":" + minute_picker.value.toString() + ":00"
                    product_add_time.logDebug()
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    txt_time_picker?.text = ""
                }
                .show()
        }

        btn_care_product_add.setOnClickListener {
            postRegister()
            finish()
        }
    }

    private fun getCareProductDialog(idx: Int) {
        val call: Call<CareProductDialogData> =
            RequestURL.service.getCareProductDialog(
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI",
                product_idx = idx
            )
        call.enqueue(object : Callback<CareProductDialogData> {
            override fun onFailure(call: Call<CareProductDialogData>, t: Throwable) {
                t.toString().logDebug()
            }

            override fun onResponse(
                call: Call<CareProductDialogData>,
                response: Response<CareProductDialogData>
            ) {
                if (response.isSuccessful) {
                    response.message()
                    val diaRespo = response.body()!!
                    val ingredient_image_uri = diaRespo.data[0].image_key
                    txt_product_add_name.text = diaRespo.data[0].product_name
                    txt_product_add_way.text = diaRespo.data[0].product_daily_dose

                    Glide.with(this@CareProductAddActivity)
                        .load(ingredient_image_uri)
                        .centerCrop()
                        .into(img_product_add)
                    toast("성공")
                } else {
                    toast("실패")
                }
            }
        })
    }

    private fun postRegister() {
        val call: Call<RegisterData> = RequestURL.service.postRegister(
            token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI",
            product_idx = 162,
            dose_daily_quantity = product_add_medicine,
            dose_start_date = product_add_date,
            dose_alarm = product_add_time
        )
        call.enqueue(
            object : Callback<RegisterData> {
                override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                    t.toString().logDebug()
                }

                override fun onResponse(
                    call: Call<RegisterData>,
                    response: Response<RegisterData>
                ) {
                    Log.d("haeeul", "${response.message()}")
                    if (response.isSuccessful) {
                        val LoginRepos: RegisterData = response.body()!!
                        val message = LoginRepos.message
                        Log.d("haeeul", "성공 ${response.body()}")
                        toast(message)
                    } else {
                        Log.d("haeeul", "실패 ${response.errorBody()?.string()}")
                        //toast("실패라능ㅠㅠ")
                    }
                }
            }
        )
    }
}
