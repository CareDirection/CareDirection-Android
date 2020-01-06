package com.example.caredirection.data

import com.example.caredirection.R

data class RvFunctionalSelectedData(
    val careFeature1:Array<String>?,
    val featureName:String
) {
    fun getDrawable(type: String): Int {
        return when (type) {
            //TODO: 여기에 장건강, 등 리소스 연결해 놓을 것
            "간건강"-> R.drawable.btn_liver
            "피로회복"->R.drawable.btn_fatigue_recovery
            "눈건강"->R.drawable.btn_brain
            "혈행개선"->R.drawable.btn_blood
            "면역력ㆍ항산화"->R.drawable.btn_immunity
            "소화기능"->R.drawable.btn_digestive
            "두뇌활동"->R.drawable.btn_brain
            "운동보조"->R.drawable.btn_muscle
            "뼈"->R.drawable.btn_bone
            else -> R.drawable.white_border_dialog_21
        }
    }
}