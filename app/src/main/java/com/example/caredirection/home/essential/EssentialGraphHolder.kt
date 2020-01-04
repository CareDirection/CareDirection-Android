package com.example.caredirection.home.essential

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvEssentialGraphData

class EssentialGraphHolder (view: View):RecyclerView.ViewHolder(view){
    val  title:TextView= view.findViewById(R.id.rv_item_graph_details_ingredient_title)
    val  shortComment:TextView=view.findViewById(R.id.rv_item_graph_short_comment)
    val  longComment:TextView=view.findViewById(R.id.rv_item_graph_details_ingredient_content)
    var  percent : Float = 0f

    fun bind(data: RvEssentialGraphData){
            title.text=data.title
            longComment.text=data.longComment
            shortComment.text=data.shortComment
            percent=data.percent
    }

}
