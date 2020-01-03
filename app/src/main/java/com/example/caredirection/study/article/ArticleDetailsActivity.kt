package com.example.caredirection.study.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvArticleDetailsData
import com.example.caredirection.data.network.ArticleDetailsData
import com.example.caredirection.data.network.IngredientData
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_article_details.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.top_bar
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleDetailsActivity : AppCompatActivity() {

    private var articleIdx: Int = 1
    private lateinit var rvArticleDetailsAdapter: ArticleDetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        val fromIngredientIntent = getIntent()
        articleIdx = fromIngredientIntent.getIntExtra("article",1)

        top_bar.top_plain_title.txt_top_bar_title.text = "아티클"
        getArticleDetailsResponse()
        rv_article_sub_view.layoutManager=LinearLayoutManager(this)
        rvArticleDetailsAdapter= ArticleDetailsAdapter(this)
        rv_article_sub_view.adapter = rvArticleDetailsAdapter
        rvArticleDetailsAdapter.data= listOf(RvArticleDetailsData("","fjfjkjllnnnn","dfsfadf"))
        rvArticleDetailsAdapter.notifyDataSetChanged()
    }
    private fun getArticleDetailsResponse(){
        val call: Call<ArticleDetailsData> = RequestURL.service.getArticleDetails(article_idx=articleIdx)
        call.enqueue(
            object :Callback<ArticleDetailsData>{
                override fun onFailure(call: Call<ArticleDetailsData>, t: Throwable) {
                    t.toString().logDebug()
                }

                override fun onResponse(
                    call: Call<ArticleDetailsData>,
                    response: Response<ArticleDetailsData>
                ) {
                    if(response.isSuccessful){
                        val detailsReso=response.body()!!.data
                        txt_article_title.text=detailsReso.main_contents.article_title
                        txt_article_date.text=detailsReso.main_contents.article_date
                        txt_article_main_comment.text=detailsReso.main_contents.article_content

                        Glide.with(this@ArticleDetailsActivity)
                            .load(detailsReso.main_contents.image_key)
                            .centerCrop()
                            .into(img_article_details)
                        val detail = mutableListOf<RvArticleDetailsData>()
                        for(item in detailsReso.sub_contents){
                            detail.add(RvArticleDetailsData(item.image_key,item.sub_article_title,item.sub_article_content))
                        }
                        rvArticleDetailsAdapter.data=detail
                        rvArticleDetailsAdapter.notifyDataSetChanged()
                    }
                }

            })
    }

}
