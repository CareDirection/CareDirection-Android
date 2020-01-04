
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.care_product.register.CareProductRegisterHolder
import com.example.caredirection.data.RvCareProductRegisterData

class CareProductRegisterAdapter (private val context: Context) :RecyclerView.Adapter<CareProductRegisterHolder>(){
    var data = listOf<RvCareProductRegisterData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareProductRegisterHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.rv_item_care_product_register,parent,false)
        return CareProductRegisterHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CareProductRegisterHolder, position: Int) {
        holder.bind(data[position])
    }


}