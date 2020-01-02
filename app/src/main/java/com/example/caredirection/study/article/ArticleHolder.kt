package com.example.caredirection.study.article

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.RvArticleData

class ArticleHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val img: ImageView = view.findViewById(R.id.img_rv_item_ingredient_article)
    val txt: TextView = view.findViewById(R.id.txt_rv_item_ingredient_article)

    fun bind(data: RvArticleData) {
        Glide.with(view)
            .load(data.img)
            .centerCrop()
            .into(img)
        txt.text = data.text

    }

}

