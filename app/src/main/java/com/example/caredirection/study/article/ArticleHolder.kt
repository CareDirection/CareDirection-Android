package com.example.caredirection.study.article

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvArticleData
import kotlinx.android.synthetic.main.fragment_ingredient_study.*

class ArticleHolder(private val view: View,private val context: Context) : RecyclerView.ViewHolder(view) {
    private var idx:Int=0
    val img: ImageView = view.findViewById(R.id.img_rv_item_ingredient_article)
    val txt: TextView = view.findViewById(R.id.txt_rv_item_ingredient_article)

    fun bind(data: RvArticleData) {
        Glide.with(view)
            .load(data.img)
            .centerCrop()
            .into(img)
        txt.text = data.text
        idx=data.index
        view.setOnClickListener(){

                val articleIntent = Intent(context, ArticleDetailsActivity::class.java)
                articleIntent.putExtra("article", idx!!)
                //articleIdxIntent=200
                startActivity(context,articleIntent, Bundle())
            }

        }

    }



