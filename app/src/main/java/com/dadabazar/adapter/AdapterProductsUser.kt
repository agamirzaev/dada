package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.R
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.fragment.ProductUserFragment
import com.squareup.picasso.Picasso

@Suppress("SENSELESS_COMPARISON")
class AdapterProductsUser(
    private val productsList: ArrayList<ProductsList>
) :
    RecyclerView.Adapter<AdapterProductsUser.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterProductsUser.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_products_user, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterProductsUser.ViewHolder, position: Int) {
        val productsList: ProductsList = productsList[position]
        viewHolder.bind(holder, productsList)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {

        }

        private val imageBasket: ImageView =
            view.findViewById(R.id.productsImageBookMarks)
        private val priceBasket: TextView =
            view.findViewById(R.id.priceProductsBookMarks)
        private val regPriceBasket: TextView =
            view.findViewById(R.id.priceRegBookMarks)
        private val nameProductBasket: TextView =
            view.findViewById(R.id.nameProductBookMarks)


        @SuppressLint("SetTextI18n")
        fun bind(holder: ViewHolder, productsList: ProductsList) {
            Picasso.get().load("https://dadabazar.online/" + productsList.getPoster())
                .into(holder.imageBasket)
            holder.priceBasket.text = productsList.getSalePrice().toString() + "₽"
            if (productsList.getRegPrice().toString() != "") {
                holder.regPriceBasket.text = productsList.getRegPrice().toString() + "₽"
            } else {
                holder.regPriceBasket.text = "0.00₽"
            }

            itemView.setOnClickListener {
                val fragment: Fragment = ProductUserFragment(
                    productsList.getId().toString(),
                    productsList.getUserId().toString()
                )
                val fragManager: FragmentManager =
                    (itemView.context as AppCompatActivity).supportFragmentManager
                val ft: FragmentTransaction = fragManager.beginTransaction()
                ft.replace(R.id.content, fragment)
                ft.commit()
            }

            holder.nameProductBasket.text = productsList.getName().toString()
            holder.nameProductBasket.maxLines = 2
            holder.nameProductBasket.isSingleLine = false
            holder.nameProductBasket.ellipsize = null
            holder.nameProductBasket.setHorizontallyScrolling(false)
        }

    }
}