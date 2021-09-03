package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.fragment.ProductFragment
import com.dadabazar.mvp.basket_add.BasketAddController
import com.dadabazar.mvp.basket_add.BasketAddPresenter
import com.dadabazar.mvp.wishlist_add.WishlistAddController
import com.dadabazar.mvp.wishlist_add.WishlistAddPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.retrofit2.adapter.rxjava2.Result.error
import com.squareup.picasso.Picasso

@Suppress("CAST_NEVER_SUCCEEDS")
class AdapterProducts(private val productsLists: ArrayList<ProductsList>) :
    RecyclerView.Adapter<AdapterProducts.ViewHolder>(), WishlistAddController.View,
    BasketAddController.View {

    private lateinit var viewHolder: ViewHolder
    private lateinit var presenter: WishlistAddPresenter
    private lateinit var presenterBasket: BasketAddPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProducts.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_products_list, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterProducts.ViewHolder, position: Int) {
        val productsList: ProductsList = productsLists[position]
        viewHolder.bind(holder, productsList)
    }

    override fun getItemCount(): Int {
        return productsLists.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            presenterBasket =
                BasketAddPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenterBasket.attachView(this@AdapterProducts)

            presenter =
                WishlistAddPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenter.attachView(this@AdapterProducts)
        }

        private val productsImage: ImageView =
            view.findViewById(R.id.productsImage)
        private val nameProducts: TextView = view.findViewById(R.id.nameProduct)
        private val priceProducts: TextView =
            view.findViewById(R.id.priceProducts)
        private val priceReg: TextView = view.findViewById(R.id.priceReg)
        private val wishlistProducts: ImageView = view.findViewById(R.id.wishlist_products)
        private val btnClickBasketUser: AppCompatButton = view.findViewById(R.id.btnClickBasketUser)
        var constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintLayout)
        private var isWishlist: Int = 0
        private var isBasket: Int = 0

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
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


            isWishlist = if (productsList.getIsWishlist().toString().toInt() == 1) {
                holder.wishlistProducts.setImageResource(R.drawable.ic_wishlist_true)
                1
            } else {
                holder.wishlistProducts.setImageResource(R.drawable.ic_wishlist_false)
                0
            }

            holder.wishlistProducts.setOnClickListener {
                if (holder.isWishlist == 0) {
                    if (Preferences.loadUserId(viewHolder.itemView.context).toString() != "") {
                        holder.isWishlist = 1
                        holder.wishlistProducts.setImageResource(R.drawable.ic_wishlist_true)
                        presenter.responseAddWishlist(
                            "2AOmTW",
                            Preferences.loadUserId(viewHolder.itemView.context).toString(),
                            productsList.getId().toString()
                        )
                    } else {
                        (viewHolder.itemView.context as MainActivity).alertDialog()
                    }
                } else {
                    if (Preferences.loadUserId(viewHolder.itemView.context).toString() != "") {
                        holder.isWishlist = 0
                        holder.wishlistProducts.setImageResource(R.drawable.ic_wishlist_false)
                        presenter.responseAddWishlist(
                            "2AOmTW",
                            Preferences.loadUserId(viewHolder.itemView.context).toString(),
                            productsList.getId().toString()
                        )
                    } else {
                        (viewHolder.itemView.context as MainActivity).alertDialog()
                    }
                }
            }



            isBasket = if (productsList.getIsBasket().toString().toInt() == 1) {
                holder.btnClickBasketUser.text = "В корзине"
                holder.btnClickBasketUser.resources.getDrawable(R.drawable.shape_basket_products_true)
                1
            } else {
                holder.btnClickBasketUser.text = "В корзину"
                0
            }
            holder.btnClickBasketUser.setOnClickListener {
                if (isBasket == 0) {
                    if (Preferences.loadUserId(viewHolder.itemView.context).toString() != "") {
                        holder.isBasket = 1
                        holder.btnClickBasketUser.text = "В корзине"
                        presenterBasket.responseAddBasket(
                            "2AOmTW",
                            Preferences.loadUserId(viewHolder.itemView.context).toString(),
                            productsList.getId().toString(),
                            1
                        )
                    } else {
                        (viewHolder.itemView.context as MainActivity).alertDialog()
                    }
                } else {
                    if (Preferences.loadUserId(viewHolder.itemView.context).toString() != "") {
                        holder.isBasket = 0
                        holder.btnClickBasketUser.text = "В корзину"
                        presenterBasket.responseAddBasket(
                            "2AOmTW",
                            Preferences.loadUserId(viewHolder.itemView.context).toString(),
                            productsList.getId().toString(),
                            1
                        )
                    } else {
                        (viewHolder.itemView.context as MainActivity).alertDialog()
                    }
                }
            }

            holder.priceReg.text = productsList.getRegPrice().toString() + "р"
            holder.nameProducts.text = productsList.getName().toString()
            holder.priceProducts.text = productsList.getSalePrice().toString() + "р"

            Picasso.get()
                .load("https://dadabazar.online/" + productsList.getPoster())
                .into(holder.productsImage)
        }
    }

    override fun getAddWishlist(addWishlist: AddWishlist) {
        if (addWishlist.getMessage().toString() == "Add") {
            val mySnackbar = Snackbar.make(
                viewHolder.constraintLayout,
                "Продукт успешно добавлен в список желаний.",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        } else {
            val mySnackbar = Snackbar.make(
                viewHolder.constraintLayout,
                "Продукт удален из списока желаний.",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
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

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}