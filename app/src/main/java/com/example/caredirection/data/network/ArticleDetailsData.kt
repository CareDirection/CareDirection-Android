package com.example.caredirection.data.network

data class ArticleDetailsData(
    val status:Int,
    val message:String,
    val data:ArticleDetailsItem
)
data class ArticleDetailsItem(
    val main_contents:ArticleDetailsMainContentItem,
    val sub_contents:List<ArticleDetailsSubContentItem>

)
data class ArticleDetailsMainContentItem(
    val image_key:String,
    val article_title:String,
    val article_content:String,
    val article_date:String
)
data class ArticleDetailsSubContentItem(
    val image_key:String,
    val sub_article_title:String,
    val sub_article_content:String
)