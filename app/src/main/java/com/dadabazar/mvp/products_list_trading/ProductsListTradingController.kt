package com.dadabazar.mvp.products_list

import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.MvpView

interface ProductsListTradingController  : MvpView {
    interface View : MvpView {
        fun getProductsListTrading(category: ArrayList<ProductsList>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notProducts()
    }

    interface Presenter : MvpView {
        fun responseProductsListTrading(user_id: String,key: String)
    }
}