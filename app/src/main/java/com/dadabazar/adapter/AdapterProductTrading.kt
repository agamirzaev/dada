package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.R
import com.dadabazar.data.model.products.ProductsList
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class AdapterProductTrading(private val products: ArrayList<ProductsList>) :
    RecyclerView.Adapter<AdapterProductTrading.ViewHolder>() {
    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterProductTrading.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_products_trading, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterProductTrading.ViewHolder, position: Int) {
        val products: ProductsList = products[position]
        viewHolder.bind(holder, products)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var idProduct: TextView = view.findViewById(R.id.idProduct)
        private var imageProduct: ImageView = view.findViewById(R.id.imageProduct)
        private var nameProduct: TextView = view.findViewById(R.id.nameProduct)
        private var priceProduct: TextView = view.findViewById(R.id.priceProduct)
        private var SKU: TextView = view.findViewById(R.id.SKU)
        private var QTY: TextView = view.findViewById(R.id.QTY)

        @SuppressLint("SetTextI18n")
        fun bind(holder: ViewHolder, products: ProductsList) {
            holder.idProduct.text = products.getId().toString()
            Picasso.get().load("https://dadabazar.online/" + products.getPoster())
                .into(holder.imageProduct)
            holder.nameProduct.text = products.getName().toString()
            holder.priceProduct.text = products.getSalePrice().toString() + " р"
            holder.SKU.text = "Нет"
            holder.QTY.text = products.getQuantity().toString()
        }

    }
}