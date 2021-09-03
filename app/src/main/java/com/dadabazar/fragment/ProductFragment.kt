package com.dadabazar.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.fragment.tabLayoutFragment.DescriptionProductFragment
import com.dadabazar.fragment.tabLayoutFragment.ReviewsProductFragment
import com.dadabazar.fragment.tabLayoutFragment.SpecificationsProductFragment
import com.dadabazar.mvp.basket_add.BasketAddController
import com.dadabazar.mvp.basket_add.BasketAddPresenter
import com.dadabazar.mvp.basket_list.qty.QTYController
import com.dadabazar.mvp.basket_list.qty.QTYPresenter
import com.dadabazar.mvp.product.ProductController
import com.dadabazar.mvp.product.ProductPresenter
import com.dadabazar.mvp.wishlist_add.WishlistAddController
import com.dadabazar.mvp.wishlist_add.WishlistAddPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso


class ProductFragment(
    private var productId: String, private var userId: String
) : Fragment(), ProductController.View, WishlistAddController.View, BasketAddController.View,
    QTYController.View {

    private lateinit var imageProduct: ImageView
    private lateinit var nameSeller: TextView
    private lateinit var imageProfileSeller: ImageView
    private lateinit var backImage: ImageView
    private lateinit var wishlistProduct: ImageView
    private lateinit var btnBasketProduct: AppCompatButton

    private lateinit var productPresenter: ProductPresenter
    private lateinit var presenterQty: QTYPresenter
    private lateinit var presenter: WishlistAddPresenter
    private lateinit var presenterAddBasket: BasketAddPresenter

    private lateinit var priceProduct: TextView
    private lateinit var regPriceProduct: TextView
    private lateinit var nameProduct: TextView
    private lateinit var rateProduct: RatingBar
    private lateinit var coutRate: TextView

    private lateinit var clickAddProduct: ImageView
    private lateinit var clickRemoveProduct: ImageView
    private lateinit var clickShapeImageView: ImageView
    private lateinit var countProducts: TextView

    private lateinit var constraintLayoutProfileSeller: ConstraintLayout
    private lateinit var nestedScrollViewProduct: NestedScrollView
    private lateinit var progressBarProduct: ProgressBar

    private lateinit var tabLayout: TabLayout
    private var counter: Int = 1
    private var isBasket: Int = 0
    private var isWishlist: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        tabLayout = view.findViewById(R.id.tabLayout)

        tabLayoutFragment(DescriptionProductFragment(productId))

        constraintLayoutProfileSeller = view.findViewById(R.id.constraintLayoutProfileSeller)

        wishlistProduct = view.findViewById(R.id.wishlist_product)
        nameSeller = view.findViewById(R.id.nameSeller)
        imageProfileSeller = view.findViewById(R.id.imageProfileSeller)
        imageProduct = view.findViewById(R.id.imageProducts)
        backImage = view.findViewById(R.id.btnBackCatalog)
        priceProduct = view.findViewById(R.id.priceProduct)
        regPriceProduct = view.findViewById(R.id.regPriceProduct)
        nameProduct = view.findViewById(R.id.nameProduct)
        rateProduct = view.findViewById(R.id.rateProduct)
        coutRate = view.findViewById(R.id.coutRate)
        clickAddProduct = view.findViewById(R.id.clickAddProduct)
        countProducts = view.findViewById(R.id.countProducts)
        clickRemoveProduct = view.findViewById(R.id.clickRemoveProduct)
        btnBasketProduct = view.findViewById(R.id.btnBasketProduct)
        clickShapeImageView = view.findViewById(R.id.clickShapeImageView)
        countProducts.text = counter.toString()
        nestedScrollViewProduct = view.findViewById(R.id.nestedScrollViewProduct)
        progressBarProduct = view.findViewById(R.id.progressBarProduct)

        presenter = WishlistAddPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@ProductFragment)

        presenterAddBasket =
            BasketAddPresenter((view.context.applicationContext as App).dataManager!!)
        presenterAddBasket.attachView(this@ProductFragment)

        productPresenter = ProductPresenter((view.context.applicationContext as App).dataManager!!)
        productPresenter.attachView(this@ProductFragment)
        productPresenter.responseProductId(
            "" + productId,
            "2AOmTW",
            Preferences.loadUserId(requireContext()).toString()
        )
        productPresenter.responseProductUser("" + userId, "2AOmTW")
        presenterQty = QTYPresenter((view.context.applicationContext as App).dataManager!!)
        presenterQty.attachView(this@ProductFragment)

        backImage.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@ProductFragment)
                .commit()
            (activity as MainActivity).backDetailsCatalogFragment()
        }

        constraintLayoutProfileSeller.setOnClickListener {
            val ft: FragmentTransaction =
                (activity as MainActivity).supportFragmentManager.beginTransaction()
            ft.replace(R.id.content, ProfileSellerFragment(userId, productId))
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
        clickShapeImageView.setOnClickListener {
            shareUrl(productId)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        tabLayoutFragment(DescriptionProductFragment(productId))
                    }
                    1 -> {
                        tabLayoutFragment(SpecificationsProductFragment())
                    }
                    2 -> {
                        tabLayoutFragment(ReviewsProductFragment())
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        return view
    }


    private fun shareUrl(productId: String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        share.putExtra(Intent.EXTRA_TEXT, "https://dadabazar.online/product/$productId")
        startActivity(Intent.createChooser(share, "Поделиться!"))
    }

    private fun tabLayoutFragment(fm: Fragment) {
        val ft: FragmentTransaction =
            (activity as MainActivity).supportFragmentManager.beginTransaction()
        ft.replace(R.id.containerTabLayout, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun getProduct(product: ArrayList<ProductsList>) {
        for (prod in product) {
            Picasso.get().load("https://dadabazar.online/" + prod.getPoster()).into(imageProduct)
            var qty = prod.getQuantity()!!.toInt()
            val qtyMax = prod.getQtyMax()!!.toInt()
            val priceProd = qty * prod.getSalePrice()!!.toInt()
            val regPriceProd = qty * prod.getRegPrice()!!.toInt()

            priceProduct.text = "$priceProd р"
            regPriceProduct.text = "$regPriceProd р"
            nameProduct.text = prod.getName().toString()
            countProducts.text = prod.getQuantity().toString()

            if (prod.getIsBasket()!!.toInt() == 1) {
                countProducts.text = prod.getQuantity().toString()
                clickAddProduct.setOnClickListener {
                    if (qty < qtyMax) {
                        countProducts.text = "${++qty}"
                        val priceProdq = qty * prod.getSalePrice()!!.toInt()
                        val regPriceProdq = qty * prod.getRegPrice()!!.toInt()
                        priceProduct.text = "$priceProdq р"
                        regPriceProduct.text = "$regPriceProdq р"
                        presenterQty.responseQTY(
                            "2AOmTW",
                            Preferences.loadUserId(requireContext()).toString(),
                            productId,
                            "plus"
                        )
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Доспупное количество: $qtyMax",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                clickRemoveProduct.setOnClickListener {
                    if (qty > 1) {
                        presenterQty.responseQTY(
                            "2AOmTW",
                            Preferences.loadUserId(requireContext()).toString(),
                            productId,
                            "minus"
                        )
                        countProducts.text = "${--qty}"
                        val priceProds = ((prod.getSalePrice()!!.toInt()) * (qty) / 1)
                        val regPriceProds = ((prod.getRegPrice()!!.toInt()) * (qty) / 1)
                        priceProduct.text = "$priceProds р"
                        regPriceProduct.text = "$regPriceProds р"
                    }
                }
            } else {
                countProducts.text = prod.getQuantity().toString()
                clickAddProduct.setOnClickListener {
                    if (counter < prod.getQtyMax()!!.toInt()) {
                        countProducts.text = "${++counter}"
                        val priceProd = counter * prod.getSalePrice()!!.toInt()
                        val regPriceProd = counter * prod.getRegPrice()!!.toInt()
                        priceProduct.text = "$priceProd р"
                        regPriceProduct.text = "$regPriceProd р"
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Доспупное количество: $qtyMax",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                clickRemoveProduct.setOnClickListener {
                    if (counter > 1) {
                        countProducts.text = "${--counter}"
                        val priceProd = ((prod.getSalePrice()!!.toInt()) * (counter) / 1)
                        val regPriceProd = ((prod.getRegPrice()!!.toInt()) * (counter) / 1)
                        priceProduct.text = "$priceProd р"
                        regPriceProduct.text = "$regPriceProd р"
                    }
                }
            }

            btnBasketProduct.setOnClickListener {
                if (isBasket == 0) {
                    if (Preferences.loadUserId(requireContext()).toString() != "") {
                        isBasket = 1
                        btnBasketProduct.text = "В корзине"
                        presenterAddBasket.responseAddBasket(
                            "2AOmTW",
                            Preferences.loadUserId(requireContext()).toString(),
                            productId,
                            counter
                        )
                    } else {
                        (activity as MainActivity).alertDialog()
                    }
                } else {
                    if (Preferences.loadUserId(requireContext()).toString() != "") {
                        isBasket = 0
                        btnBasketProduct.text = "В корзину"
                        presenterAddBasket.responseAddBasket(
                            "2AOmTW",
                            Preferences.loadUserId(requireContext()).toString(),
                            productId,
                            counter
                        )
                    } else {
                        (activity as MainActivity).alertDialog()
                    }
                }
            }

            isWishlist = if (prod.getIsWishlist().toString().toInt() == 1) {
                wishlistProduct.setImageResource(R.drawable.ic_iswishlistbasket)
                1
            } else {
                wishlistProduct.setImageResource(R.drawable.ic_bookmarks)
                0
            }
            wishlistProduct.setOnClickListener {
                if (isWishlist == 0) {
                    if (Preferences.loadUserId(requireContext()).toString() != "") {
                        isWishlist = 1
                        wishlistProduct.setImageResource(R.drawable.ic_iswishlistbasket)
                        presenter.responseAddWishlist(
                            "2AOmTW",
                            Preferences.loadUserId(requireContext()).toString(),
                            productId
                        )
                    } else {
                        (activity as MainActivity).alertDialog()
                    }
                } else {
                    if (Preferences.loadUserId(requireContext()).toString() != "") {
                        isWishlist = 0
                        wishlistProduct.setImageResource(R.drawable.ic_bookmarks)
                        presenter.responseAddWishlist(
                            "2AOmTW",
                            Preferences.loadUserId(requireContext()).toString(),
                            productId
                        )
                    } else {
                        (activity as MainActivity).alertDialog()
                    }
                }
            }


            isBasket = if (prod.getIsBasket().toString().toInt() == 1) {
                btnBasketProduct.text = "В корзине"
                1
            } else {
                btnBasketProduct.text = "В корзину"
                0
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getUserProduct(users: Users) {
        Picasso.get().load("https://dadabazar.online/" + users.getResponse()!!.getAvatar())
            .into(imageProfileSeller)
        nameSeller.text = users.getResponse()!!.getFname() + " " + users.getResponse()!!.getLname()
    }

    override fun getAddWishlist(addWishlist: AddWishlist) {
        if (addWishlist.getMessage().toString() == "Add") {
            val mySnackbar = Snackbar.make(
                constraintLayoutProfileSeller,
                "Продукт успешно добавлен в список желаний.",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        } else {
            val mySnackbar = Snackbar.make(
                constraintLayoutProfileSeller,
                "Продукт удален из списока желаний.",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        }
    }

    override fun getAddBasketList(addBasket: AddBasket) {
        if (addBasket.getMessage().toString() == "Add") {
            val mySnackbar = Snackbar.make(
                constraintLayoutProfileSeller,
                "Этот предмет добавлен в корзину!",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        } else {
            val mySnackbar = Snackbar.make(
                constraintLayoutProfileSeller,
                "Этот предмет удален из корзины!",
                Snackbar.LENGTH_LONG
            )
            mySnackbar.show()
        }
    }

    override fun showProgress() {
        nestedScrollViewProduct.visibility = View.GONE
        progressBarProduct.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        nestedScrollViewProduct.visibility = View.VISIBLE
        progressBarProduct.visibility = View.GONE
    }

    override fun noConnection() {

    }

    override fun getQTY(responseQty: ResponseQty) {
    }
}