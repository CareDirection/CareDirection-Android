package com.example.caredirection.study

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER




class FragmentStudySymptom : Fragment() {

    private lateinit var rv_study_symptom: RecyclerView
    private lateinit var rv_study_symptom_adapter : StudySymptomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredient_study_symptom, container, false)

        //리사이클러뷰 연결
        rv_study_symptom.adapter = rv_study_symptom_adapter
        rv_study_symptom_adapter = StudySymptomAdapter(context!!)
        rv_study_symptom = view.findViewById(R.id.rv_ingredient_study_symptom)
        rv_study_symptom.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        data = mutableListOf(
            StudySymptomitem("오메가3", true),
            StudySymptomitem("종합비타민"),
            StudySymptomitem("홍삼"),
            StudySymptomitem("로얄젤리"),
            StudySymptomitem("비타민A"),
            StudySymptomitem("비타민D")
        )



        return view
    }
    private var data = mutableListOf<StudySymptomitem>()

inner class StudySymptomAdapter(private val context: Context) :
    RecyclerView.Adapter<StudySymptomAdapter.StudySymptomHolder>() {


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
            view.findViewById(R.id.selector_rv_item_nutrient)
        val txt_rv_study_nutrient_symptom_item: CheckedTextView =
            view.findViewById(R.id.txt_rv_search_nutrient)

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
            }
        }
    }
}

    data class StudySymptomitem(
        val Symptomitem: String,
        val check: Boolean = false
    )
}