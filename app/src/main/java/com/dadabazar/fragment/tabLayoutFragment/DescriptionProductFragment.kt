package com.dadabazar.fragment.tabLayoutFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dadabazar.App
import com.dadabazar.R
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.product.ProductController
import com.dadabazar.mvp.product.ProductPresenter
import com.dadabazar.utills.Preferences

class DescriptionProductFragment(private val productId: String) : Fragment(),
    ProductController.View {
    private lateinit var productPresenter: ProductPresenter
    private lateinit var descriptionProduct: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description_product, container, false)
        productPresenter = ProductPresenter((view.context.applicationContext as App).dataManager!!)
        productPresenter.attachView(this@DescriptionProductFragment)
        productPresenter.responseProductId(
            "" + productId,
            "2AOmTW",
            Preferences.loadUserId(requireContext()).toString()
        )

        descriptionProduct = view.findViewById(R.id.descriptionProduct)
        return view
    }

    override fun getProduct(product: ArrayList<ProductsList>) {
        for (prod in product) {
            descriptionProduct.text = prod.getDescription().toString()
        }
    }

    override fun getUserProduct(users: Users) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}