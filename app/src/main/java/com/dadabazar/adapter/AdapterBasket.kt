package com.dadabazar.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.R
import com.dadabazar.data.model.Checked
import com.dadabazar.data.model.basketlist.Basket
import com.dadabazar.data.model.basketlist.DelBasket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.fragment.BasketFragment
import com.dadabazar.mvp.basket_del.BasketDelController
import com.dadabazar.mvp.basket_del.BasketDelPresenter
import com.dadabazar.mvp.basket_list.checked.BasketCheckedController
import com.dadabazar.mvp.basket_list.checked.BasketCheckedPresenter
import com.dadabazar.mvp.wishlist_add.WishlistAddController
import com.dadabazar.mvp.wishlist_add.WishlistAddPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

@Suppress("SENSELESS_COMPARISON")
class AdapterBasket(
    private val basketList: ArrayList<Basket>,
    private val fragment: Fragment
) :
    RecyclerView.Adapter<AdapterBasket.ViewHolder>(), WishlistAddController.View,
    BasketDelController.View, BasketCheckedController.View {

    private lateinit var viewHolder: ViewHolder
    private lateinit var presenter: WishlistAddPresenter
    private lateinit var presenterDelete: BasketDelPresenter
    private lateinit var presenterChecked: BasketCheckedPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBasket.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_basket, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterBasket.ViewHolder, position: Int) {
        val baskets: Basket = basketList[position]
        viewHolder.bind(holder, baskets)
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            presenterDelete =
                BasketDelPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenterDelete.attachView(this@AdapterBasket)

            presenter =
                WishlistAddPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenter.attachView(this@AdapterBasket)

            presenterChecked =
                BasketCheckedPresenter((itemView.context.applicationContext as App).dataManager!!)
            presenterChecked.attachView(this@AdapterBasket)

        }

        private var imageWishlistBasket: ImageView = view.findViewById(R.id.imageWishlistBasket)
        private var textWishlistBasket: TextView = view.findViewById(R.id.textWishlistBasket)
        private val imageBasket: ImageView = view.findViewById(R.id.imageBasket)
        private val priceBasket: TextView = view.findViewById(R.id.priceBasket)
        private val regPriceBasket: TextView = view.findViewById(R.id.regPriceBasket)
        private val nameProductBasket: TextView = view.findViewById(R.id.nameProductBasket)
        private val colorProductBasket: TextView = view.findViewById(R.id.colorProductBasket)
        private val clickAddProductTop: ImageView = view.findViewById(R.id.clickAddProduct)
        private val clickAddProductBottom: ImageView = view.findViewById(R.id.clickRemoveProduct)
        private val countBasket: TextView = view.findViewById(R.id.countProducts)
        private val clickWishlistBasket: ConstraintLayout =
            view.findViewById(R.id.clickWishlistBasket)
        private val clickDeleteBasket: ConstraintLayout = view.findViewById(R.id.clickDeleteBasket)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.ConstraintLayout)
        private val clickCheckBox: CheckBox = view.findViewById(R.id.clickCheckBox)
        private var isWishlist: Int = 0

        @SuppressLint("SetTextI18n")
        fun bind(holder: ViewHolder, basket: Basket) {
            if (basket.getPoster()!!.toString() != "") {
                Picasso.get().load("https://dadabazar.online/" + basket.getPoster())
                    .into(holder.imageBasket)
            }

            holder.colorProductBasket.text = "Цвет: " + basket.getVariationType().toString()
            holder.nameProductBasket.text = basket.getName().toString()

            var qty = basket.getQuantity()!!.toInt()
            val qty_max = basket.getQtyMax()!!.toInt()
            var priceProd = qty * basket.getSalePrice()!!.toInt()
            var regPriceProd: Int
            if (basket.getRegPrice()!!.toString() != "") {
                regPriceProd = qty * basket.getRegPrice()!!.toInt()
                holder.regPriceBasket.text = regPriceProd.toInt().toString() + "p"
            }else{
                holder.regPriceBasket.text = "0р"
            }

            if (basket.getSalePrice()!!.toString() != "") {
                holder.priceBasket.text = priceProd.toString() + "p"
            }

            holder.countBasket.text = basket.getQuantity().toString()
            holder.clickCheckBox.tag = position


            if (basket.getIsChecked()!!.toInt() == 1) {
                holder.clickCheckBox.isChecked = true
            } else {
                holder.clickCheckBox.isChecked = false

            }

            holder.clickCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    presenterChecked.responseChecked(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        basket.getId().toString()
                    )
                } else {
                    presenterChecked.responseChecked(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        basket.getId().toString()
                    )
                }
            }

            holder.isWishlist = if (basket.getIsWishlist().toString().toInt() == 1) {
                holder.textWishlistBasket.text = "В избранном"
                holder.imageWishlistBasket.setImageResource(R.drawable.ic_iswishlistbasket)
                1
            } else {
                holder.imageWishlistBasket.setImageResource(R.drawable.ic_bookmarks)
                holder.textWishlistBasket.text = "В избранное"
                0
            }

            holder.clickWishlistBasket.setOnClickListener {
                if (holder.isWishlist == 0) {
                    holder.isWishlist = 1
                    holder.textWishlistBasket.text = "В избранном"
                    holder.imageWishlistBasket.setImageResource(R.drawable.ic_iswishlistbasket)
                    presenter.responseAddWishlist(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        basket.getId().toString()
                    )
                } else {
                    holder.isWishlist = 0
                    holder.imageWishlistBasket.setImageResource(R.drawable.ic_bookmarks)
                    holder.textWishlistBasket.text = "В избранное"
                    presenter.responseAddWishlist(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        basket.getId().toString()
                    )
                }
            }

            holder.clickAddProductTop.setOnClickListener {
                if (qty < qty_max) {
                    holder.countBasket.text = "${++qty}"
                    priceProd = qty * basket.getSalePrice()!!.toInt()
                    regPriceProd = qty * basket.getRegPrice()!!.toInt()
                    holder.priceBasket.text = "$priceProd р"
                    holder.regPriceBasket.text = "$regPriceProd р"
                    presenterChecked.responseUpdateQty(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        basket.getId().toString(),
                        "plus"
                    )
                } else {
                    Toast.makeText(
                        viewHolder.itemView.context,
                        "Доспупное количество: $qty_max",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            holder.clickAddProductBottom.setOnClickListener {
                if (qty > 1) {
                    holder.countBasket.text = "${--qty}"
                    priceProd = ((basket.getSalePrice()!!.toInt()) * (qty) / 1)
                    regPriceProd = ((basket.getRegPrice()!!.toInt()) * (qty) / 1)
                    holder.priceBasket.text = "$priceProd р"
                    holder.regPriceBasket.text = "$regPriceProd р"
                    presenterChecked.responseUpdateQty(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        basket.getId().toString(),
                        "minus"
                    )
                }
            }

            holder.clickDeleteBasket.setOnClickListener {
                alertDialogDetele(basket.getId()!!.toInt())
            }
        }

        private fun deleteItem(position: Int) {
            try {
                basketList.removeFirst()
                notifyItemRemoved(position)
                notifyDataSetChanged()
                notifyItemRangeChanged(position, basketList.size - 1)
                if (basketList.size == 0) {
                    (fragment as BasketFragment).notBasketProductsList()
                }
            } catch (e: Exception) {

            }
        }

        private fun alertDialogDetele(id_prod: Int): AlertDialog.Builder {
            val builder: AlertDialog.Builder = AlertDialog.Builder(viewHolder.itemView.context)
            builder.setTitle("Удалить")
                .setMessage("Вы уверены что хотите удалить этот продукт?")
                .setCancelable(false)
                .setNegativeButton("Отмена") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("Удалить") { dialog, _ ->
                    presenterDelete.responseDelBasket(
                        "2AOmTW",
                        Preferences.loadUserId(viewHolder.itemView.context).toString(),
                        id_prod.toString()
                    )
                    deleteItem(position)
                }
            val alert: AlertDialog = builder.create()
            alert.show()
            return builder
        }
    }

    override fun getChecked(checked: Checked) {
        if (checked.getStatus().toString() == "true") {
            val mySnackbar = Snackbar.make(
                viewHolder.constraintLayout,
                "Продукт выбран",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        } else {
            val mySnackbar = Snackbar.make(
                viewHolder.constraintLayout,
                "Продукт отменен из выбранных",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        }
    }

    override fun getUpdateQty(responseQty: ResponseQty) {

    }

    override fun getAddWishlist(addWishlist: AddWishlist) {
        try {
            if (addWishlist.getMessage().toString() == "Add") {
                val mySnackbar = Snackbar.make(
                    viewHolder.constraintLayout,
                    "Продукт добавлен в список желаний.",
                    Snackbar.LENGTH_LONG
                )
                mySnackbar.show()
            } else {
                val mySnackbar = Snackbar.make(
                    viewHolder.constraintLayout,
                    "Продукт удален из списка желаний.",
                    Snackbar.LENGTH_LONG
                )
                mySnackbar.show()
            }
        } catch (e: Exception) {

        }
    }

    override fun getDelBasketList(delBasket: DelBasket) {
        try {
            if (delBasket != null) {
                val mySnackbar = Snackbar.make(
                    viewHolder.constraintLayout,
                    "Продукт удален из корзины!",
                    Snackbar.LENGTH_LONG
                )
                mySnackbar.show()
            }
        } catch (e: Exception) {

        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}