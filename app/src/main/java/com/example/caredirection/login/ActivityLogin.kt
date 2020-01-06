package com.example.caredirection.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.data.network.LoginData
import com.example.caredirection.network.RequestURL
import com.example.caredirection.research.ResearchActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {

    val REQUEST_CODE_LOGIN_ACTIVITY =1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_login.setPadding(0, statusBarHeight(this), 0, 0)

        // 자동로그인
        val id = LoginController.getID(this)
        val pw = LoginController.getPW(this)
        if(id.isNotEmpty()&&pw.isNotEmpty()){
            val intent = Intent(this, ResearchActivity::class.java)
            // id 함께 전달
            intent.putExtra("id", id)
            startActivity(intent)
        }

        // 회원가입버튼 클릭
        btn_activity_register?.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(view : View?) {
                    // 회원가입 페이지로 이동
                    val register_intent = Intent(this@ActivityLogin, ActivityRegister::class.java)
                    startActivityForResult(register_intent,REQUEST_CODE_LOGIN_ACTIVITY)
                }
            }
        )

        // 로그인버튼 클릭
        btn_activity_login?.setOnClickListener {
            val id = edt_activity_login_id?.text.toString()
            val pw = edt_activity_login_pwd?.text.toString()

            // ID와 PW 비었는지 검사
            if (id.isEmpty()) {
                Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(pw.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 로그인 요청
            postLoginResponse(id, pw)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE_LOGIN_ACTIVITY){
            if(resultCode== Activity.RESULT_OK){
                val id=data!!.getStringExtra("id")
                val pw=data!!.getStringExtra("pw")
                edt_activity_login_id?.setText(id)
                edt_activity_login_pwd?.setText(pw)
            }
        }
    }

    private fun postLoginResponse(id: String, pw: String){
        val call: Call<LoginData> = RequestURL.service.postLogin(id,pw)
        call.enqueue(
            object : Callback<LoginData> {
                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    t.toString().logDebug()
                }
                override fun onResponse(
                    call: Call<LoginData>,
                    response: Response<LoginData>
                ) {
                    Log.d("haeeul", "${response.message()}")
                    if (response.isSuccessful) {
                        val LoginRepos : LoginData = response.body()!!
                        val token = LoginRepos.data.token
                        Log.d("haeeul", "성공 ${response.body()}")
                        LoginController.setID(this@ActivityLogin,id)
                        LoginController.setPW(this@ActivityLogin,pw)
                        TokenController.setAccessToken(this@ActivityLogin,token)
                        val intent = Intent(this@ActivityLogin, ResearchActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("haeeul","실패 ${response.errorBody()?.string()}")
                        toast("잘못된 아이디 또는 비밀번호입니다.")
                    }
                }
            }
        )
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}
