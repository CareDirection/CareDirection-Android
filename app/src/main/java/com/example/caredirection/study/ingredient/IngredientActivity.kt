package com.example.caredirection.study.ingredient


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.data.network.IngredientData
import com.example.caredirection.data.network.IngredientInfoItem
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_ingredient.*
import kotlinx.android.synthetic.main.activity_ingredient.view.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IngredientActivity : AppCompatActivity() {

    private lateinit var rvIngredientAdapter: IngredientAdapter
    private lateinit var Ingredient : String
    private  var ingredientIdx : Int=0
    private lateinit var ingredient_comment :String
    private lateinit var ingredient_image: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)
        val fromIngredientIntent = getIntent()
        Ingredient=fromIngredientIntent.getStringExtra("ingredient")

        top_bar.top_plain_text.txt_top_bar_title.text = "성분"
        txt_ingredient_ingredient.text=Ingredient

        ingredientIdx=when(Ingredient){
            "홍삼"->35
            "오메가 3"->41
            "밀크씨슬"->37
            "루테인"->38
            "유산균"->36
            "비타민 D"->27
            else->0
        }

        toast(ingredientIdx.toString())

        getIngredientInfoResponse()
    }

    private fun getIngredientInfoResponse(){
        val call: Call<IngredientData> = RequestURL.service.getIngredientInfo( "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6IjMifQ.-KEfwDT3c7yMpSO3ujWo_oZLa2cEHyKriDts_2BEvfg",ingredientIdx)

        call.enqueue(
            object : Callback<IngredientData>{
                override fun onFailure(call: Call<IngredientData>, t: Throwable) {
                    "성분 값을 가지고 오지 못하였습니다.".logDebug()
                }

                override fun onResponse(
                    call: Call<IngredientData>,
                    response: Response<IngredientData>
                ) {
                    if (response.isSuccessful) {
                        val ingredientRepos: IngredientData = response.body()!!
                        txt_article_title.text = ingredientRepos.data[0].nutrient_common_description
                        val ingredient_image_uri = ingredientRepos.data[0].image_key

                        Glide.with(this@IngredientActivity)
                            .load(ingredient_image_uri)
                            .centerCrop()
                            .into(img_article_details)
                    }

                }

            }
        )

    }
}
