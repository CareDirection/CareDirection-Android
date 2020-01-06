package com.example.caredirection.home.functional

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.study.ActivityStudySymptom

class FunctionalAllFeatureHolder(view: View, var context: Context) : RecyclerView.ViewHolder(view){
    val imgFunctionalAll: ImageView = view.findViewById(R.id.img_rv_item_functional_all)
    val txtFunctionalAll : TextView = view.findViewById(R.id.txt_rv_item_functional_all)

    fun bind(data: RvFunctionalAllData){
        imgFunctionalAll.setImageResource(data.img)
        txtFunctionalAll.text=data.txt

        itemView.setOnClickListener{
            val intent = Intent(context, ActivityStudySymptom::class.java)
            intent.putExtra("efficacy",txtFunctionalAll.text.toString())
            startActivity(context ,intent, null)
        }
    }
}