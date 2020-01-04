package com.example.caredirection.common

import android.view.View
import androidx.fragment.app.DialogFragment
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import retrofit2.Call
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.CareProductDoseData
import com.example.caredirection.home.HomeFragment
import com.example.caredirection.login.TokenController
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.dialog_care_product_check.*
import retrofit2.Callback
import retrofit2.Response


class CustomDialogFragment(
    private val position: Int,
    private val callback: ((position: Int, isChecked: Boolean) -> Unit)
) :
    DialogFragment() {

    var productName: String = "dkjsljf"
    var idx: Int = 0
    var img: String = ""
    var isCheck: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.dialog_care_product_check, container, false)
        var txtDialogCareProductCancel =
            rootView.findViewById<TextView>(R.id.txt_dialog_care_product_cancel)
        var txtDialogCareProductConfirm =
            rootView.findViewById<TextView>(R.id.txt_dialog_care_product_confirm)

        txtDialogCareProductCancel.setOnClickListener { dismiss() }
        txtDialogCareProductConfirm.setOnClickListener {
            Log.d("Malibin Debug", "그런건 필요없어 idx : $idx")
            postCareProductDoseResponse()

        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txt_dialog_care_product_name.text = productName

        Glide.with(this)
            .load(img)
            .centerCrop()
            .into(img_dialog_care_product)

    }

    override fun onStart() {
        super.onStart()


        val window = dialog!!.window
        window!!.setBackgroundDrawableResource(R.drawable.white_border_dialog_21)
    }

    private fun postCareProductDoseResponse() {
        val call: Call<CareProductDoseData> = RequestURL.service.postCareProductDose(
            TokenController.getAccessToken(context!!)!!,
            idx
        )
        //todo 토큰값 집어넣기
        call.enqueue(object : Callback<CareProductDoseData> {
            override fun onFailure(call: Call<CareProductDoseData>, t: Throwable) {
                t.toString().logDebug()
                dismiss()
            }

            override fun onResponse(
                call: Call<CareProductDoseData>,
                response: Response<CareProductDoseData>
            ) {
                "dkljaldjlfa".logDebug()
                if (response.isSuccessful) {
                    val doseRespo = response.body()!!
                    val isDosed = doseRespo.message
                    if (isDosed == "복용 등록 성공") {
                        isCheck = true
                    }
                    Log.d("Malibin","${response.body()}")

                    callback.invoke(position,isCheck)
                    dismiss()
                }
                //this.toast("이미 복용하셨습니다!")
                dismiss()
            }

        })
    }


}