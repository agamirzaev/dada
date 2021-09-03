package com.dadabazar.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.fragment.tabLayoutFragment.DescriptionProductFragment
import com.dadabazar.fragment.tabLayoutFragment.ReviewsProductFragment
import com.dadabazar.fragment.tabLayoutFragment.SpecificationsProductFragment
import com.dadabazar.mvp.product.ProductController
import com.dadabazar.mvp.product.ProductPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso


class ProductUserFragment(
    private var productId: String, private var userId: String
) : Fragment(), ProductController.View {

    private lateinit var productPresenter: ProductPresenter
    private lateinit var constraintLayoutProfileSeller: ConstraintLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var clickShapeImageView: ImageView

    private lateinit var priceProduct: TextView
    private lateinit var regPriceProduct: TextView
    private lateinit var nameProduct: TextView
    private lateinit var rateProduct: RatingBar
    private lateinit var imageProduct: ImageView
    private lateinit var nameSeller: TextView
    private lateinit var imageProfileSeller: ImageView
    private lateinit var backImage: ImageView
    private lateinit var btnEditProduct: AppCompatButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_user, container, false)
        tabLayout = view.findViewById(R.id.tabLayout)
        constraintLayoutProfileSeller = view.findViewById(R.id.constraintLayoutProfileSeller)
        clickShapeImageView = view.findViewById(R.id.clickShapeImageView)

        nameSeller = view.findViewById(R.id.nameSeller)
        imageProfileSeller = view.findViewById(R.id.imageProfileSeller)
        imageProduct = view.findViewById(R.id.imageProducts)
        backImage = view.findViewById(R.id.btnBackCatalog)
        priceProduct = view.findViewById(R.id.priceProduct)
        regPriceProduct = view.findViewById(R.id.regPriceProduct)
        nameProduct = view.findViewById(R.id.nameProduct)
        rateProduct = view.findViewById(R.id.rateProduct)
        btnEditProduct = view.findViewById(R.id.btnEditProduct)

        productPresenter = ProductPresenter((view.context.applicationContext as App).dataManager!!)
        productPresenter.attachView(this@ProductUserFragment)
        productPresenter.responseProductId(
            "" + productId, "2AOmTW",
            Preferences.loadUserId(requireContext()).toString()
        )
        productPresenter.responseProductUser("" + userId, "2AOmTW")

        tabLayoutFragment(DescriptionProductFragment(productId))

        backImage.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@ProductUserFragment)
                .commit()

            val ft: FragmentTransaction =
                (activity as MainActivity).supportFragmentManager.beginTransaction()
            ft.replace(R.id.content, ProfileUserFragment())
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }

        clickShapeImageView.setOnClickListener {
            shareUrl(productId)
        }

        constraintLayoutProfileSeller.setOnClickListener {
            val ft: FragmentTransaction =
                (activity as MainActivity).supportFragmentManager.beginTransaction()
            ft.replace(R.id.content, ProfileUserFragment())
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
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
        startActivity(Intent.createChooser(share, "Share link!"))
    }

    private fun tabLayoutFragment(fm: Fragment) {
        val ft: FragmentTransaction =
            (activity as MainActivity).supportFragmentManager.beginTransaction()
        ft.replace(R.id.containerTabLayout, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun getProduct(product: ArrayList<ProductsList>) {
        for (prod in product) {
            Picasso.get().load("https://dadabazar.online/" + prod.getPoster()).into(imageProduct)
            priceProduct.text = prod.getSalePrice().toString() + " р"
            regPriceProduct.text = prod.getRegPrice().toString() + " р"
            nameProduct.text = prod.getName().toString()
            btnEditProduct.setOnClickListener {
                (activity as MainActivity).loadFragment(
                    EditProductProfileFragment(
                        prod.getId().toString()
                    )
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getUserProduct(users: Users) {
        Picasso.get().load("https://dadabazar.online/" + users.getResponse()!!.getAvatar())
            .into(imageProfileSeller)
        nameSeller.text = users.getResponse()!!.getFname() + " " + users.getResponse()!!.getLname()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}