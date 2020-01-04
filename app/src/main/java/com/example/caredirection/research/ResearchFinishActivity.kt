package com.example.caredirection.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.example.caredirection.R
import com.example.caredirection.care_product.register.CareProductActivity
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.network.LifeCycleData
import com.example.caredirection.login.TokenController
import com.example.caredirection.network.RequestURL
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_finish.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResearchFinishActivity : AppCompatActivity() {

    private lateinit var keeper : ResearchKeeper
    private var user_survey_item_value1 : String =""
    private var user_survey_item_value2 : String =""
    private lateinit var user_survey_item_value3 : String
    private lateinit var user_survey_item_value4 : String
    private lateinit var user_survey_item_value5 : String
    private lateinit var user_survey_item_value6 : String
    private lateinit var user_survey_item_value7 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_finish)

        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_finish.setPadding(0, statusBarHeight(this), 0, 0)

        btn_finish_next.setOnClickListener{
            keeper.researchFinish = 1

            keeper.disease?.sorted()?.forEach {
                user_survey_item_value1 = "$user_survey_item_value1$it,"
            }

            keeper.symptom?.sorted()?.forEach {
                user_survey_item_value2 = "$user_survey_item_value2$it,"
            }

            //질병, 증상
            var dRange = IntRange(0, user_survey_item_value1.length-2)
            var sRange = IntRange(0, user_survey_item_value2.length-2)

            //담배
            when(keeper.cigarette){
                R.id.btn_alcohol_1 -> user_survey_item_value3 = "네"
                R.id.btn_alcohol_2 -> user_survey_item_value3 = "아니오"
            }

            //술
            when(keeper.alcohol){
                R.id.btn_alcohol_3 -> user_survey_item_value4 = "네"
                R.id.btn_alcohol_4 -> user_survey_item_value4 = "아니오"
            }

            //야외활동
            when(keeper.activity){
                R.id.btn_activity_1 -> user_survey_item_value5 = "4시간 이상"
                R.id.btn_activity_2 -> user_survey_item_value5 = "1시간 ~ 4시간"
                R.id.btn_activity_3 -> user_survey_item_value5 = "1시간 이하"
            }

            //녹황색 채소 섭취
            when(keeper.vegetable){
                R.id.btn_vegetable_1 -> user_survey_item_value6 = "7회 이상"
                R.id.btn_vegetable_2 -> user_survey_item_value6 = "5회 ~ 6회"
                R.id.btn_vegetable_3 -> user_survey_item_value6 = "3회 ~ 4회"
                R.id.btn_vegetable_4 -> user_survey_item_value6 = "2회 이하"
            }

            //운동
            when(keeper.exercise){
                R.id.btn_exercise_1 -> user_survey_item_value7 = "4회 이상"
                R.id.btn_exercise_2 -> user_survey_item_value7 = "2회 ~ 3회"
                R.id.btn_exercise_3 -> user_survey_item_value7 = "1회 이하"
            }

            postLifeCycle(user_survey_item_value1,user_survey_item_value2,user_survey_item_value3,user_survey_item_value4,user_survey_item_value5,user_survey_item_value6,user_survey_item_value7)

            startActivity(Intent(this, CareProductActivity::class.java))
        }
    }

    private fun postLifeCycle(s1:String,s2:String,s3:String,s4:String,s5:String,s6:String,s7:String){
        val token = TokenController.getAccessToken(this)

        if(token != null) {
            val call: Call<LifeCycleData> =
                RequestURL.service.postLifeCycle(token, s1, s2, s3, s4, s5, s6, s7)
            call.enqueue(
                object : Callback<LifeCycleData> {
                    override fun onFailure(call: Call<LifeCycleData>, t: Throwable) {
                        t.toString().logDebug()
                    }

                    override fun onResponse(
                        call: Call<LifeCycleData>,
                        response: Response<LifeCycleData>
                    ) {
                        Log.d("haeeul", "${response.message()}")
                        if (response.isSuccessful) {
                            val InfoRepos: LifeCycleData = response.body()!!
                            val message = InfoRepos.message
                            Log.d("haeeul", "라이프스타일 성공 ${response.body()}")
                            //toast(message)
                        } else {
                            Log.d("haeeul", "라이프스타일 실패 ${response.errorBody()?.string()}")
                            //toast("실패")
                        }
                    }
                }
            )
        }
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}
