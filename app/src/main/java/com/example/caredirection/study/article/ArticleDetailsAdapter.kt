package com.example.caredirection.study.article

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvArticleDetailsData

class ArticleDetailsAdapter (private val context :Context) :RecyclerView.Adapter<ArticleDetailsHolder>(){


    var data = listOf<RvArticleDetailsData>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleDetailsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_article_details,parent,false)

        return ArticleDetailsHolder(view)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: ArticleDetailsHolder, position: Int) {
        holder.bind(data[position])
    }




}