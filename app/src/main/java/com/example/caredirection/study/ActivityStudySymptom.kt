package com.example.caredirection.study

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.EfficacyStudyResponse
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_ingredient_study_symptom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ActivityStudySymptom : AppCompatActivity() {
    var text = ""
    private lateinit var rv_study_symptom: RecyclerView
    private lateinit var rv_study_symptom_adapter : StudySymptomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_study_symptom)

        text = intent.getStringExtra("efficacy")
        txt_ingredient_study_symptom_title.setText(text)

        rv_study_symptom_adapter = StudySymptomAdapter(this@ActivityStudySymptom)
        rv_study_symptom = findViewById(R.id.rv_ingredient_study_symptom)

        rv_study_symptom.layoutManager = LinearLayoutManager(this@ActivityStudySymptom, LinearLayoutManager.HORIZONTAL,false)

        getEfficacyStudyList(text)
        rv_study_symptom.adapter = rv_study_symptom_adapter

    }


    inner class StudySymptomAdapter(private val context: Context) :
        RecyclerView.Adapter<StudySymptomAdapter.StudySymptomHolder>() {

        var data = mutableListOf<StudySymptomitem>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudySymptomHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.rv_study_ingrient_symptom, parent, false)

            return StudySymptomHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: StudySymptomHolder, position: Int) {
            holder.bind(position)
        }

        inner class StudySymptomHolder(view: View) : RecyclerView.ViewHolder(view) {
            val txt_study_nutrient_symptom_item: TextView =
                view.findViewById(R.id.txt_rv_study_nutrient_symptom_item)
            val selector_rv_item_study_nutrient_symptom: CheckedTextView =
                view.findViewById(R.id.txt_rv_study_nutrient_symptom_item)
            val txt_rv_study_nutrient_symptom_item: CheckedTextView =
                view.findViewById(R.id.selector_rv_item_study_nutrient_symptom)

            fun bind(position: Int) {
                val item = data[position]
                txt_study_nutrient_symptom_item.text = item.Symptomitem
                selector_rv_item_study_nutrient_symptom.isChecked = item.check
                txt_rv_study_nutrient_symptom_item.isChecked = item.check

                itemView.setOnClickListener{
                    (0 until data.size).forEach {
                        data[it] = data[it].copy(check = false)
                    }
                    data[position] = data[position].copy(check = true)
                    rv_study_symptom_adapter.notifyDataSetChanged()


                    getEfficacyStudyList(text,position)


                }
            }
        }
    }

    data class StudySymptomitem(
        val Symptomitem: String,
        val check: Boolean = false
    )

    private fun getEfficacyStudyList(search_word: String){
        val call: Call<EfficacyStudyResponse> = RequestURL.service.getEfficacyStudyList(search_word)

        call.enqueue(
            object : Callback<EfficacyStudyResponse> {
                override fun onFailure(call: Call<EfficacyStudyResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<EfficacyStudyResponse>,
                    response: Response<EfficacyStudyResponse>
                ) {
                    val efficacyResponseDataList = response.body()!!.data

                    (0 until efficacyResponseDataList.size!!).forEach {
                        if (it == 0){
                            rv_study_symptom_adapter.data.add(StudySymptomitem(efficacyResponseDataList[it].nutrientName,true))
                        }else{
                            rv_study_symptom_adapter.data.add(StudySymptomitem(efficacyResponseDataList[it].nutrientName,false))
                        }
                        rv_study_symptom_adapter.notifyDataSetChanged()
                    }

                    Glide.with(applicationContext).load(efficacyResponseDataList[0].imageLocation).into(iv_study_symptom)
                    txt_ingredient_study_symptom_content.text = efficacyResponseDataList[0].nutrientEfficacyComment
                }
            })
    }

    private fun getEfficacyStudyList(search_word: String, position: Int){
        val call: Call<EfficacyStudyResponse> = RequestURL.service.getEfficacyStudyList(search_word)

        call.enqueue(
            object : Callback<EfficacyStudyResponse> {
                override fun onFailure(call: Call<EfficacyStudyResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<EfficacyStudyResponse>,
                    response: Response<EfficacyStudyResponse>
                ) {
                    val efficacyResponseDataList = response.body()!!.data

                    Glide.with(applicationContext).load(efficacyResponseDataList[position].imageLocation).into(iv_study_symptom)
                    txt_ingredient_study_symptom_content.text = efficacyResponseDataList[position].nutrientEfficacyComment

                }
            })
    }
}
