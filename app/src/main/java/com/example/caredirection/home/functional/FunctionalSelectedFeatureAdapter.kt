package com.example.caredirection.home.functional

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.data.RvFunctionalSelectedData

class FunctionalSelectedFeatureAdapter (private val context: Context) : RecyclerView.Adapter<FunctionalSelectedFeatureHolder>(){

    var data = arrayOf<RvFunctionalSelectedData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FunctionalSelectedFeatureHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_functional_selected,parent,false)
        return FunctionalSelectedFeatureHolder(view)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: FunctionalSelectedFeatureHolder, position: Int) {
        holder.bind(data[position])
    }

}