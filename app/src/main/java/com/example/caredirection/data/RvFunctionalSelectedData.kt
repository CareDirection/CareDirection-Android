package com.example.caredirection.data

import com.example.caredirection.R

data class RvFunctionalSelectedData(
    val careFeature1:List<String>?,
    val featureName:String
) {
    fun getDrawable(type: String): Int {
        return when (type) {
            //TODO: 여기에 장건강, 등 리소스 연결해 놓을 것
            "장건강" -> R.drawable.bg_research_gradient
            else -> R.drawable.white_border_dialog_21
        }
    }
}