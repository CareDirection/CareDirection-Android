package com.example.caredirection.home
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import kotlin.math.min

class TopDownDialogHolder(context: Context) : BaseAdapter() {

    data class Child(
        val index: String,
        val name: String
    )

    var data = listOf<Child>()

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return min(data.size + 1, MAX_CHILD_NUM)
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return when(getViewType(position)) {
            ENTRY -> {
                "${data.size} ${getViewType(position)}, $position ".logDebug()

                val current = data[position]

                val view = layoutInflater.inflate(R.layout.rv_item_home_top_down_user, parent, false)

                view.setOnClickListener {
                    //TODO: 유저 변경하는 이벤트 연결 서버통신
                    "$current is clicked".logDebug()
                }

                view.findViewById<TextView>(R.id.txt_home_top_down_child_user_name).text = current.name

                view.findViewById<View>(R.id.btn_home_top_down_delete).setOnClickListener {
                    //TODO: 유저 삭제하는 서버 통신 연결
                    "$current is deleted".logDebug()
                }

                view
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.rv_item_home_top_down_add_user, parent, false)

                view.findViewById<View>(R.id.btn_home_top_down_child_add_user_name).setOnClickListener {
                    //TODO: Child 추가하는 화면으로 이동하기
                    "child 추가 하는 화면으로 이동하기".logDebug()
                }

                view
            }
        }
    }

    private fun getViewType(position: Int): Int {
        return if (data.size < MAX_CHILD_NUM && position == data.size) FOOTER else ENTRY
    }

    companion object {
        const val FOOTER = 0
        const val ENTRY = 1

        const val MAX_CHILD_NUM = 4
    }
}
