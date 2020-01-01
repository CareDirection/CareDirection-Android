package com.example.caredirection.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductData
import kotlinx.android.synthetic.main.fragment_home.view.*

class CareProductAdapter(private val context: Context ) : RecyclerView.Adapter<CareProductHolder>(){

    //adapter는 viewoHolder로 변경할 data를 가지고 있는다.
    var data = listOf<RvCareProductData>()

    private lateinit var onClick : View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareProductHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.rv_item_care_home,parent,false)
        view.setOnClickListener(onClick)
        return CareProductHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CareProductHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setOnClick(l : View.OnClickListener){
        onClick = l
    }


}