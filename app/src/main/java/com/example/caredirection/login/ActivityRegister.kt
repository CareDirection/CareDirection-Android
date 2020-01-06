package com.example.caredirection.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.data.network.IdData
import com.example.caredirection.data.network.SignUpData
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityRegister : AppCompatActivity() {

    private var idCheck: Boolean = false
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var pwCk: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 중복확인 버튼 클릭
        btn_activity_register_checked.setOnClickListener {
            id = edt_activity_register_id.text.toString()
            //요청
            postIdResponse(id)
        }

        // 회원가입완료 버튼 클릭
        btn_register_confirm.setOnClickListener {
            id = edt_activity_register_id.text.toString()
            pw = edt_activity_register_pwd.text.toString()
            pwCk = edt_activity_register_pwd_check.text.toString()

            // 빈 곳 체크
            if (id.isBlank() || pw.isBlank() || pwCk.isBlank()) {
                toast("빈칸없이 모두 작성해주세요.")
                return@setOnClickListener
            }

            // 비밀번호와 비밀번호 확인이 일치하는지 체크
            if (pw != pwCk) {
                toast("비밀번호를 확인해주세요.")
                return@setOnClickListener
            }

            // 회원가입 요청
            postSignupResponse(id, pw)
        }
    }

    private fun postSignupResponse(id: String, pw: String) {
        val call: Call<SignUpData> = RequestURL.service.postSignUp(id,pw)
        call.enqueue(
            object : Callback<SignUpData> {
                override fun onFailure(call: Call<SignUpData>, t: Throwable) {
                    t.toString().logDebug()
                }
                override fun onResponse(
                    call: Call<SignUpData>,
                    response: Response<SignUpData>
                ) {
                    Log.d("haeeul", "${response.message()}")
                    if (response.isSuccessful) {
                        Log.d("haeeul", "성공 ${response.body()}")
                        val intent = Intent()
                        // 회원가입 성공한 아이디 전달
                        intent.putExtra("id", id)
                        intent.putExtra("pw", pw)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    } else {
                        Log.d("haeeul","실패 ${response.errorBody()?.string()}")
                        toast("회원가입에 실패했습니다.")
                    }
                }
            }
        )
    }

    private fun postIdResponse(id: String) {
        val call: Call<IdData> = RequestURL.service.postIdCheck(id)
        call.enqueue(
            object : Callback<IdData> {
                override fun onFailure(call: Call<IdData>, t: Throwable) {
                    t.toString().logDebug()
                }
                override fun onResponse(
                    call: Call<IdData>,
                    response: Response<IdData>
                ) {
                    Log.d("haeeul", "${response.message()}")
                    if (response.isSuccessful) {
                        Log.d("haeeul", "${response.body()}")
//                        val idRepos: IdData = response.body()!!
//                        val message = idRepos.message
//                        toast(message)
//                        idRepos.status.toString().logDebug()
                        toast("사용가능한 아이디입니다.")
                    } else {
                        Log.d("haeeul","${response.errorBody()?.string()}")
                        toast("중복되는 아이디입니다.")
                    }
                }
            }
        )
    }
}
