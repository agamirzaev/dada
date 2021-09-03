package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.R
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.fragment.ProductFragment
import com.squareup.picasso.Picasso

@Suppress("CAST_NEVER_SUCCEEDS")
class AdapterProductsTrends(
    private val productsLists: ArrayList<ProductsList>,
    private val fragment: Fragment
) : RecyclerView.Adapter<AdapterProductsTrends.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterProductsTrends.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product_trends, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterProductsTrends.ViewHolder, position: Int) {
        val productsList: ProductsList = productsLists[position]
        viewHolder.bind(holder, productsList)
    }

    override fun getItemCount(): Int {
        return productsLists.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val productsImage: ImageView =
            view.findViewById(R.id.productsImage)
        private val priceProducts: TextView =
            view.findViewById(R.id.priceProducts)
        private val priceReg: TextView = view.findViewById(R.id.priceReg)
        private var wishlistProducts: ImageView = view.findViewById(R.id.wishlistProducts)
        private val addBasketUser: AppCompatButton = view.findViewById(R.id.addBasketUser)

        @SuppressLint("SetTextI18n")
        fun bind(holder: ViewHolder, productsList: ProductsList) {

            itemView.setOnClickListener {
                val fragment: Fragment = ProductFragment(
                    productsList.getId().toString(),
                    productsList.getUserId().toString()
                )
                val fragManager: FragmentManager =
                    (itemView.context as AppCompatActivity).supportFragmentManager
                val ft: FragmentTransaction = fragManager.beginTransaction()
                ft.replace(R.id.content, fragment)
                ft.commit()
            }

            holder.addBasketUser.setOnClickListener {

            }

            holder.wishlistProducts.setOnClickListener {

            }

            holder.priceReg.text = productsList.getRegPrice().toString() + "р"
            holder.priceProducts.text = productsList.getSalePrice().toString() + "р"

            Picasso.get()
                .load("https://dadabazar.online/" + productsList.getPoster())
                .error(R.drawable.error)
                .into(holder.productsImage)
        }
    }
}