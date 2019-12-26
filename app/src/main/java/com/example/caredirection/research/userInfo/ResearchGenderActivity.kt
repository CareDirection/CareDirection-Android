package com.example.caredirection.research.userInfo

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import com.example.caredirection.R
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.*
import com.example.caredirection.common.toast
import kotlinx.android.synthetic.main.activity_research_gender.*


class ResearchGenderActivity : AppCompatActivity() {


    private var txt_gender_nametitle: TextView? = null
    private var txt_gender_namesubtitle: TextView? = null
    private var txt_gender_subtitle: TextView? = null
    private var btn_gender_next: Button? = null
    private var chtxt_gender_women: CheckedTextView? = null
    private var chtxt_gender_man: CheckedTextView? = null
    private var txt_year_picker: TextView? = null

    private var women_selec: Boolean = false
    private var man_selec: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_gender)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_gender.setPadding(0, statusBarHeight(this), 0, 0)



        makeController()
        setColorInPartitial()
    }
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    // 사용자 입력받아서 초기화
    private fun makeController(){
        //edt_username = findViewById((R.id.edt_username))
        txt_gender_nametitle = findViewById(R.id.txt_gender_nametitle)
        txt_gender_namesubtitle = findViewById(R.id.txt_gender_namesubtitle)
        btn_gender_next = findViewById(R.id.btn_gender_next)
        txt_gender_subtitle = findViewById(R.id.txt_gender_subtitle)
        chtxt_gender_women = findViewById(R.id.chtxt_gender_women)
        chtxt_gender_man = findViewById(R.id.chtxt_gender_man)
        txt_year_picker = findViewById(R.id.txt_year_picker)

        val name: String = intent.getStringExtra("username")

        txt_gender_nametitle?.text = name + "님,"
        txt_gender_namesubtitle?.text = name + "님만의"

        btn_gender_next?.setOnClickListener{
//            val name = edt_username?.text.toString()
//
//            // 이름 빈칸일 경우,
//            if(name.isEmpty()){
//                toast("입력")
//            }
//            else{
//                //btn_name_next.
//                //btn_name_next.setBackgroundResource(R.drawable.yellow_border)
//
                val disease_intent = Intent(this,ResearchDiseaseActivity::class.java)
                disease_intent.putExtra("username",name)
                toast("되냐")
                startActivity(disease_intent)
//            }
        }

        chtxt_gender_women?.setOnClickListener{
            chtxt_gender_women?.isChecked = true
            chtxt_gender_man?.isChecked = false
        }

        chtxt_gender_man?.setOnClickListener{
            chtxt_gender_women?.isChecked = false
            chtxt_gender_man?.isChecked = true
        }

        txt_year_picker?.setOnClickListener{
//            val builder = AlertDialog.Builder(this)
//            val dialogView = layoutInflater.inflate(R.layout.year_picker, null)
//            val dialogText = dialogView.findViewById<NumberPicker>(R.id.picker_year)
//
//            builder.setView(dialogView)
//                .setPositiveButton("확인") { dialogInterface, i ->
//                    txt_year_picker?.text = dialogText.toString()
//                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
//
//                }
//                .setNegativeButton("취소") { dialogInterface, i ->
//                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
//                }
//                .show()
        }
    }

    // 강조타이틀 설정
    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_gender_subtitle?.text.toString())

        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            0,
            9,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_gender_subtitle?.text = builder
    }
}
