package com.example.caredirection.study.article

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.RvArticleDetailsData

class ArticleDetailsHolder (private val view: View):RecyclerView.ViewHolder(view){
    val txtSubTitle : TextView =view.findViewById(R.id.txt_rv_item_article_sub_title)
    val imgSub : ImageView =view.findViewById(R.id.img_rv_item_article_sub)
    val txtSubComment : TextView =view.findViewById(R.id.txt_rv_item_article_sub_content)

    fun bind(data:RvArticleDetailsData){

        txtSubTitle.text=data.title
        Glide.with(view)
            .load(data.img)
            .centerCrop()
            .into(imgSub)
        txtSubComment.text=data.content
    }
}