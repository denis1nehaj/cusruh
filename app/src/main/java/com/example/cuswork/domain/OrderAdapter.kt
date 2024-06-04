import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cuswork.R
import com.example.cuswork.domain.Order

class OrderAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textView1: TextView
        val textView2: TextView
        val textView3: TextView
        val textView4: TextView
        val textView5: TextView
        val textView6: TextView
        val byn = "BYN"
        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.price)
            textView1 = view.findViewById(R.id.widthistory)
            textView2 = view.findViewById(R.id.lengthhistory)
            textView3 = view.findViewById(R.id.highthistory)
            textView4 =  view.findViewById(R.id.materialhistory)
            textView5 = view.findViewById(R.id.furniturehistory)
            textView6 = view.findViewById(R.id.typehistory)
        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("order_list", "order price = ${orders[position].price}")
        viewHolder.textView.text = orders[position].price.toString()

        Log.d("order_list","order with = ${orders[position].width}")
        viewHolder.textView1.text = orders[position].width.toString()

        Log.d("order_list","order length = ${orders[position].length}")
        viewHolder.textView2.text = orders[position].length.toString()

        Log.d("order_list","order height = ${orders[position].height}")
        viewHolder.textView3.text = orders[position].height.toString()

        Log.d("order_list","order material = ${orders[position].material!!.title}")
        viewHolder.textView4.text = orders[position].material!!.title//добавить еще .price

        Log.d("order_list","order furniture = ${orders[position].fitting}")
        viewHolder.textView5.text = orders[position].fitting!!.title//добавить еще .price

        Log.d("order_list","order type = ${orders[position].furniture}")
        viewHolder.textView6.text = orders[position].furniture!!.title
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = orders.size

}