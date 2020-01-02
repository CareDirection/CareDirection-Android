package com.example.caredirection.study.article

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvArticleData


class ArticleAdapter (private val context: Context): RecyclerView.Adapter<ArticleHolder>(){

    var data= arrayOf<RvArticleData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_ingredient_article,parent,false)

        return ArticleHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(data[position])
    }



}