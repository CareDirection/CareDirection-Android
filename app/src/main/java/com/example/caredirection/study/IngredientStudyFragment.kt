package com.example.caredirection.study

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvArticleData
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.data.RvIngredientData
import com.example.caredirection.data.network.ArticleListData
import com.example.caredirection.home.functional.FunctionalAllFeatureAdapter
import com.example.caredirection.network.RequestURL
import com.example.caredirection.study.article.ArticleAdapter
import com.example.caredirection.study.ingredient.IngredientActivity
import com.example.caredirection.study.ingredient.IngredientAdapter
import kotlinx.android.synthetic.main.fragment_ingredient_study.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudyFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var rvIngredientAdapter: IngredientAdapter
    private var IngredientIdx = ""
    private var articleIdxIntent: Int = 200

    private lateinit var rvArticleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        top_bar_ingredient_study.txt_top_bar_title.text = "성분학습"

        //성분 리싸이클러뷰
        rv_ingredient_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvIngredientAdapter = IngredientAdapter(context!!)
        rvIngredientAdapter.setOnClick(this)
        rv_ingredient_view.adapter = rvIngredientAdapter
        rvIngredientAdapter.data = listOf(
            RvIngredientData(R.color.colorIngredient1, "홍삼"),
            RvIngredientData(R.color.colorIngredient2, "오메가 3"),
            RvIngredientData(R.color.colorIngredient3, "밀크씨슬"),
            RvIngredientData(R.color.colorIngredient4, "루테인"),
            RvIngredientData(R.color.colorIngredient5, "유산균"),
            RvIngredientData(R.color.colorIngredient6, "비타민 D")
        )


        //효능 리싸이클러뷰
        //전체기능리사이클러뷰 레이아웃 설정
        rv_ingredient_efficacy.layoutManager = GridLayoutManager(context, 3)
        //어댑더 정의
        val rvFunctionalAllAdapter = FunctionalAllFeatureAdapter(context!!)
        //뷰에 어댑터 연결
        rv_ingredient_efficacy.adapter = rvFunctionalAllAdapter
        //더미 데이터 넣어주기
        rvFunctionalAllAdapter.data = listOf(
            RvFunctionalAllData(R.drawable.btn_liver, "간겅강"),
            RvFunctionalAllData(R.drawable.btn_fatigue_recovery, "피로회복"),
            RvFunctionalAllData(R.drawable.btn_eye, "눈건강"),
            RvFunctionalAllData(R.drawable.btn_blood, "혈행개선"),
            RvFunctionalAllData(R.drawable.btn_immunity, "면역력 활성화"),
            RvFunctionalAllData(R.drawable.btn_digestive, "소화기능"),
            RvFunctionalAllData(R.drawable.btn_brain, "두뇌활동"),
            RvFunctionalAllData(R.drawable.btn_muscle, "운동보조"),
            RvFunctionalAllData(R.drawable.btn_bone, "뼈")
        )

        rv_ingredient_article_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //어댑더 정의
        rvArticleAdapter = ArticleAdapter(context!!)
        //뷰에 어댑터 연결
        rvArticleAdapter.setOnClick(this, rv_ingredient_article_view)
        rv_ingredient_article_view.adapter = rvArticleAdapter
//        rvArticleAdapter.data = arrayOf(
//            RvArticleData("ㅇㄹㅇ", "ㅇㄹ너래ㅑㄴ얼"),
//            RvArticleData("ㅇㄹㅇ", "ㅇㄹ너래ㅑㄴ얼"),
//            RvArticleData("ㅇㄹㅇ", "ㅇㄹ너래ㅑㄴ얼")
//        )
        articleIdxIntent.toString().logDebug()
        getArticleListRessponse()
    }

    //recycler view click 이벤트 처리를 위해
    override fun onClick(v: View?) {

        //position
//        val articleIdx = rv_ingredient_article_view.getChildAdapterPosition(v!!)
//        articleIdxIntent = rvArticleAdapter.data[articleIdx].index
//
//        articleIdxIntent.toString().logDebug()
//        if (articleIdxIntent != 200) {
//            val articleIntent = Intent(context, ArticleDetailsActivity::class.java)
//            articleIntent.putExtra("article", articleIdxIntent!!)
//            //articleIdxIntent=200
//            startActivity(articleIntent)
//        }

        val idx = rv_ingredient_view.getChildAdapterPosition(v!!)
        //데이터가 담긴 배열의 idx 번째 데이터를 가져옴.
        // Toast.makeText(context, idx.toString(), Toast.LENGTH_SHORT).show()

        IngredientIdx = rvIngredientAdapter.data[idx].text
        // Toast.makeText(context, Ingredient , Toast.LENGTH_SHORT).show()
        //startActivity(functional_intent)
        articleIdxIntent.toString().logDebug()
        if (IngredientIdx != "") {

            val IngredientIntent = Intent(context, IngredientActivity::class.java)
            IngredientIntent.putExtra("ingredient", IngredientIdx)
            startActivity(IngredientIntent)
        }

        //rvCareProductAdapter.data[idx]
    }

    private fun getArticleListRessponse() {
        val call: Call<ArticleListData> = RequestURL.service.getArticleList()
        call.enqueue(
            object : Callback<ArticleListData> {
                override fun onFailure(call: Call<ArticleListData>, t: Throwable) {
                    t.toString().logDebug()
                }

                override fun onResponse(
                    call: Call<ArticleListData>,
                    response: Response<ArticleListData>
                ) {
                    if (response.isSuccessful) {

                        val articleListRespo = response.body()!!.data
                        val articles = mutableListOf<RvArticleData>()
                        for (item in articleListRespo) {
                            articles.add(
                                RvArticleData(
                                    item.article_idx,
                                    item.image_key,
                                    item.article_title
                                )
                            )
                        }
                        rvArticleAdapter.data = articles.toTypedArray()
                        rvArticleAdapter.notifyDataSetChanged()
                    }
                }

            }
        )

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_study, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

