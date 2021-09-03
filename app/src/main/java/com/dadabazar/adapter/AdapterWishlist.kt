package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.R
import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.data.model.wishlist.DelWishlist
import com.dadabazar.data.model.wishlist.Wishlist
import com.dadabazar.fragment.WishlistFragment
import com.dadabazar.mvp.basket_add.BasketAddController
import com.dadabazar.mvp.basket_add.BasketAddPresenter
import com.dadabazar.mvp.wishlist_add.WishlistAddController
import com.dadabazar.mvp.wishlist_add.WishlistAddPresenter
import com.dadabazar.mvp.wishlist_del.WishlistDelController
import com.dadabazar.mvp.wishlist_del.WishlistDelPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

@Suppress("SENSELESS_COMPARISON")
class AdapterWishlist(
    private val productsList: ArrayList<Wishlist>,
    private val fragment: Fragment
) :
    RecyclerView.Adapter<AdapterWishlist.ViewHolder>(), WishlistAddController.View,
    BasketAddController.View {

    private lateinit var viewHolder: ViewHolder
    private lateinit var presenter: WishlistAddPresenter
    private lateinit var presenterAddBasket: BasketAddPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWishlist.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_wishlist, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterWishlist.ViewHolder, position: Int) {
        val wishlist: Wishlist = productsList[position]
        viewHolder.bind(holder, wishlist)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            presenter =
                WishlistAddPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenter.attachView(this@AdapterWishlist)

            presenterAddBasket =
                BasketAddPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenterAddBasket.attachView(this@AdapterWishlist)
        }

        private val imageBasket: ImageView =
            view.findViewById(R.id.productsImageBookMarks)
        private val priceBasket: TextView =
            view.findViewById(R.id.priceProductsBookMarks)
        private val regPriceBasket: TextView =
            view.findViewById(R.id.priceRegBookMarks)
        private val nameProductBasket: TextView =
            view.findViewById(R.id.nameProductBookMarks)

        private val clickDeleteBasket: ImageView =
            view.findViewById(R.id.clickDeleteBasketBookMarks)

        private val btnBasketProduct: AppCompatButton = view.findViewById(R.id.btnBasketProduct)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintLayout)
        private var isBasket: Int = 0

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(holder: ViewHolder, wishlist: Wishlist) {
            Picasso.get().load("https://dadabazar.online/" + wishlist.getPoster())
                .into(holder.imageBasket)
            holder.priceBasket.text = wishlist.getSalePrice().toString() + " р"
            holder.regPriceBasket.text = wishlist.getRegPrice().toString() + " р"

            holder.isBasket = if (wishlist.getIsBasket().toString() == "1") {
                holder.btnBasketProduct.text = "В корзине"
                holder.btnBasketProduct.resources.getDrawable(R.drawable.shape_basket_products_true)
                1
            } else {
                holder.btnBasketProduct.text = "В корзину"
                0
            }
            holder.btnBasketProduct.setOnClickListener {
                if (isBasket == 0) {
                    holder.isBasket = 1
                    holder.btnBasketProduct.text = "В корзине"
                    presenterAddBasket.responseAddBasket(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        wishlist.getId().toString(), 1
                    )
                } else {
                    holder.isBasket = 0
                    holder.btnBasketProduct.text = "В корзину"
                    presenterAddBasket.responseAddBasket(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        wishlist.getId().toString(), 1
                    )
                }
            }

            holder.clickDeleteBasket.setOnClickListener {
                presenter.responseAddWishlist(
                    "2AOmTW",
                    Preferences.loadUserId(viewHolder.itemView.context).toString(),
                    wishlist.getId().toString()
                )
                removeItem(position)
            }
            holder.nameProductBasket.text = wishlist.getName().toString()
        }


        private fun removeItem(position: Int) {
            try {
                productsList.removeFirst()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, productsList.size - 1)
                if (productsList.size == 0) {
                    (fragment as WishlistFragment).notWishlistProducts()
                }
            } catch (e: Exception) {
            }
        }
    }

    override fun getAddBasketList(addBasket: AddBasket) {
        if (addBasket.getMessage().toString() == "Add") {
            val mySnackbar = Snackbar.make(
                viewHolder.constraintLayout,
                "Этот предмет добавлен в корзину!",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        } else {
            val mySnackbar = Snackbar.make(
                viewHolder.constraintLayout,
                "Этот предмет удален из корзины!",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        }
    }

    override fun getAddWishlist(addWishlist: AddWishlist) {
        try {
            if (addWishlist.getMessage().toString() != "Add") {
                val mySnackbar = Snackbar.make(
                    viewHolder.constraintLayout,
                    "Продукт успешно удален из списка желаний.",
                    Snackbar.LENGTH_LONG
                )
                mySnackbar.show()
            }
        } catch (e: Exception) {

        }
    }

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun noConnection() {}
}