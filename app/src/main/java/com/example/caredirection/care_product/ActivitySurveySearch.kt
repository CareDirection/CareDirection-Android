package com.example.caredirection.care_product

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
import com.example.caredirection.R

class ActivitySurveySearch : AppCompatActivity() {

    private lateinit var rv_servey_serach: RecyclerView
    private lateinit var rv_servey_serach_adapter: SurveySearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_search)

        rv_servey_serach_adapter = SurveySearchAdapter(this@ActivitySurveySearch)


        rv_servey_serach = findViewById(R.id.rv_activity_survey_search)
        rv_servey_serach.layoutManager = LinearLayoutManager(this@ActivitySurveySearch)

        data = mutableListOf(
            SurveySearchItem("오메가3", true),
            SurveySearchItem("종합비타민"),
            SurveySearchItem("홍삼"),
            SurveySearchItem("로얄젤리"),
            SurveySearchItem("비타민A"),
            SurveySearchItem("비타민D")
        )

        rv_servey_serach.adapter = rv_servey_serach_adapter

    }


    private var data = mutableListOf<SurveySearchItem>()

    inner class  SurveySearchAdapter(private val context: Context) : RecyclerView.Adapter<SurveySearchAdapter.SurveySearchHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveySearchHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.rv_survey_search, parent, false)

            return SurveySearchHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: SurveySearchHolder, position: Int) {
            holder.bind(position)
        }

        inner class SurveySearchHolder(view: View) : RecyclerView.ViewHolder(view){


            val checked_rv_servey_search: CheckedTextView = view.findViewById(R.id.checked_rv_servey_search)
            val txt_rv_search_survey_item_kname: TextView = view.findViewById(R.id.txt_rv_search_survey_item_kname)




            fun bind(position: Int) {
                val item = data[position]
                checked_rv_servey_search.isChecked = item.check
                txt_rv_search_survey_item_kname.text = item.Kname

                itemView.setOnClickListener{
                    (0 until data.size).forEach {
                        data[it] = data[it].copy(check = false)
                    }
                    data[position] = data[position].copy(check = true)
                    rv_servey_serach_adapter.notifyDataSetChanged()
                }
                }
        }
    }

}

data class SurveySearchItem(
    val Kname: String,
    val check: Boolean = false
)
