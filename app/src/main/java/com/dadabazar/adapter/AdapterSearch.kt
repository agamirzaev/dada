package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.R
import com.dadabazar.data.model.searchProducts.ProductsSearch
import com.squareup.picasso.Picasso

@Suppress("SENSELESS_COMPARISON")
class AdapterSearch(
    private var productsSearch: ArrayList<ProductsSearch>,
    private val fragment: Fragment
) : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSearch.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_search, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterSearch.ViewHolder, position: Int) {
        val prodSearch: ProductsSearch = productsSearch[position]
        viewHolder.bind(holder, prodSearch)
    }

    override fun getItemCount(): Int {
        return productsSearch.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageSearch: ImageView = view.findViewById(R.id.imageSearch)
        private val priceSearch: TextView = view.findViewById(R.id.priceSearch)
        private val regPriceSearch: TextView = view.findViewById(R.id.regPriceSearch)
        private val nameProductSearch: TextView = view.findViewById(R.id.nameProductSearch)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.ConstraintLayout)

        @SuppressLint("SetTextI18n")
        fun bind(holder: ViewHolder, prodSearch: ProductsSearch) {
            Picasso.get().load("https://dadabazar.online/" + prodSearch.getPoster())
                .into(holder.imageSearch)
            holder.nameProductSearch.text = prodSearch.getName().toString()

            holder.priceSearch.text = prodSearch.getSalePrice().toString()
            holder.regPriceSearch.text = prodSearch.getRegPrice().toString()

        }
    }
}