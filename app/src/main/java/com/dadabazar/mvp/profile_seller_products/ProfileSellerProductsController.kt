package com.dadabazar.mvp.profile_seller_products

import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.MvpView

interface ProfileSellerProductsController : MvpView {
    interface View : MvpView {
        fun getProductsProfileSeller(productsList: ArrayList<ProductsList>)
        fun getProfileSeller(users: Users)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun nowProductsUserSeller()
    }

    interface Presenter : MvpView {
        fun responseProductsProfileSeller(key: String, user_id: String,current_id: String)
        fun responseProfileSeller(user_id: String, key: String)
    }
}