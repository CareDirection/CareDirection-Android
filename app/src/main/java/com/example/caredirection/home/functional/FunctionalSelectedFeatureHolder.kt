package com.example.caredirection.home.functional

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalSelectedData

class FunctionalSelectedFeatureHolder(view: View) : RecyclerView.ViewHolder(view){
    data class ViewUnit(
        val container: View,
        val text: TextView,
        val image: ImageView
    )
    val txt_rv_item_functional_selected_4:TextView=view.findViewById(R.id.txt_rv_item_functional_selected_4)
    val rvFunctionalDatas = listOf(
        ViewUnit(
            view.findViewById(R.id.rv_item_functional_selected_1),
            view.findViewById(R.id.txt_rv_item_functional_selected_1),
            view.findViewById(R.id.img_rv_item_functional_selected_1)
        ),
        ViewUnit(
            view.findViewById(R.id.rv_item_functional_selected_2),
            view.findViewById(R.id.txt_rv_item_functional_selected_2),
            view.findViewById(R.id.img_rv_item_functional_selected_2)
        ),
        ViewUnit(
            view.findViewById(R.id.rv_item_functional_selected_3),
            view.findViewById(R.id.txt_rv_item_functional_selected_3),
            view.findViewById(R.id.img_rv_item_functional_selected_3)
        )
    )

    fun bind(item: RvFunctionalSelectedData){
        item.careFeature1?.forEachIndexed { index, data ->
            val current = rvFunctionalDatas[index]
            current.container.isVisible = true
            current.text.text = data
            current.image.setImageDrawable(
                itemView.context.getDrawable(item.getDrawable(data))
            )
        }
        txt_rv_item_functional_selected_4.text =item.featureName
    }
}