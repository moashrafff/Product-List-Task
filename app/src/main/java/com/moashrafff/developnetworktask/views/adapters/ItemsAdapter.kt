package com.moashrafff.developnetworktask.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moashrafff.developnetworktask.R
import com.moashrafff.developnetworktask.data.model.Product
import javax.inject.Inject

class ItemsAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((item: Product) -> Unit)? = null
    private var itemsList = mutableListOf<Product>()

    private val STOCK_MORE_THAN_FIFTY: Int = 0
    private val STOCK_LESS_THAN_FIFTY: Int = 2


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<Product>) {
        itemsList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val product = itemsList[position]
        if (product.stock > 50) {
            return STOCK_MORE_THAN_FIFTY
        }
        return STOCK_LESS_THAN_FIFTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val firstView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        val secondView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_with_big_stock, parent, false)

        if (viewType == STOCK_LESS_THAN_FIFTY) {
            return ItemViewHolder(firstView);
        } else {
            return secondItemViewHolder(secondView);
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == STOCK_LESS_THAN_FIFTY) {
            val holder : ItemViewHolder = holder as ItemViewHolder
            val product = itemsList[position]
            holder.bind(product)
            holder.itemView.setOnClickListener {
                onItemClick!!.invoke(product)
            }
        }else{
            val holder : secondItemViewHolder = holder as secondItemViewHolder
            val product = itemsList[position]
            holder.bind(product)
            holder.itemView.setOnClickListener {
                onItemClick!!.invoke(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return  itemsList.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPrice: TextView = itemView.findViewById(R.id.tv_price)
        val itemName: TextView = itemView.findViewById(R.id.tv_title)
        val itemDescription: TextView = itemView.findViewById(R.id.tv_description)
        var product: Product =
            Product(1, "hhh", "sss", 3, 3.0, 3.0, 5, "s", "s", "s", listOf("a", "b"))

        fun bind(product: Product) {
            this.product = product
            itemPrice.text = product.price.toString()+ "LE"
            itemName.text = product.title
            itemDescription.text = product.description

        }


    }

    class secondItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPrice: TextView = itemView.findViewById(R.id.s_tv_price)
        val itemName: TextView = itemView.findViewById(R.id.s_tv_title)
        val itemDescription: TextView = itemView.findViewById(R.id.s_tv_description)
        var product: Product =
            Product(1, "hhh", "sss", 3, 3.0, 3.0, 5, "s", "s", "s", listOf("a", "b"))

        fun bind(product: Product) {
            this.product = product
            itemPrice.text = product.price.toString()
            itemName.text = product.title
            itemDescription.text = product.description
        }

    }


}