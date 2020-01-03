package com.example.caredirection.study.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_article_details.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.top_bar
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*

class ArticleDetailsActivity : AppCompatActivity() {

    private var articleIdx: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        val fromIngredientIntent = getIntent()
        articleIdx = fromIngredientIntent.getIntExtra("article",1)

        top_bar.top_plain_title.txt_top_bar_title.text = "아티클"
    }
}
