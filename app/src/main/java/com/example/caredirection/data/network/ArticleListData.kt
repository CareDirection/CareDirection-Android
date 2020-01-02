package com.example.caredirection.data.network

data class ArticleListData(
    val status : Int,
    val message : String,
    val data:List<ArticleListItem>
)
data class ArticleListItem(
    val article_idx:Int,
    val article_title:String,
    val image_key:String
)