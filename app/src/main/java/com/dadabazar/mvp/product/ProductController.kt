package com.dadabazar.mvp.product

import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.MvpView

interface ProductController : MvpView {
    interface View : MvpView {
        fun getProduct(product: ArrayList<ProductsList>)
        fun getUserProduct(users: Users)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseProductId(id_product: String,key:String,user_id: String)
        fun responseProductUser(user_id: String,key:String)
    }
}