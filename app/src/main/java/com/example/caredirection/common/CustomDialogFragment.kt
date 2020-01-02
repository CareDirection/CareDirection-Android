package com.example.caredirection.common

import android.view.View
import androidx.fragment.app.DialogFragment
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.caredirection.R
import kotlinx.android.synthetic.main.dialog_care_product_check.*


class CustomDialogFragment : DialogFragment(){

    var productName:String="dkjsljf"
    var amount:String="1일 기준"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View=inflater.inflate(R.layout.dialog_care_product_check,container,false)
        var txtDialogCareProductCancel=rootView.findViewById<TextView>(R.id.txt_dialog_care_product_cancel)
        var txtDialogCareProductConfirm=rootView.findViewById<TextView>(R.id.txt_dialog_care_product_confirm)

        txtDialogCareProductCancel.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
            }
        })
        txtDialogCareProductConfirm.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //todo 뵥용확인을 눌렀을 떄 서버와 통신
            }

        })
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txt_dialog_care_product_name.text=productName
        txt_dialog_amount.text=amount

    }
    override fun onStart() {
        super.onStart()


        val window = dialog!!.window
        window!!.setBackgroundDrawableResource(R.drawable.white_border_dialog_21)
    }


}