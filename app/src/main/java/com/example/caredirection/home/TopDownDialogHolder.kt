package com.example.caredirection.home
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.caredirection.R

class TopDownDialogHolder(context: Context, private val isGrid: Boolean, private val count: Int) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return count
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        var view: View? = convertView

        view = layoutInflater.inflate(R.layout.dialog_top_down_home_list_item, parent, false)


            viewHolder = ViewHolder(
                view!!.findViewById(R.id.text_view),
                view!!.findViewById(R.id.image_view)
            )
            view.tag = viewHolder

        val context = parent.context
        when (position) {
            0 -> {
                viewHolder.textView.text = "엄마"
                viewHolder.imageView.setImageResource(R.color.colorRed)
            }
            1 -> {
                viewHolder.textView.text = "엄마"
                viewHolder.imageView.setImageResource(R.color.colorRed)
            }
            else -> {
                viewHolder.textView.text = "엄마"
                viewHolder.imageView.setImageResource(R.color.colorRed)
            }
        }

        return view!!
    }

    data class ViewHolder(val textView: TextView, val imageView: ImageView)

}