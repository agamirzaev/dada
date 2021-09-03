package com.dadabazar.mvp.products_list

import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.MvpView

interface ProductsListController  : MvpView {
    interface View : MvpView {
        fun getProductsList(category: ArrayList<ProductsList>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notProducts()
    }

    interface Presenter : MvpView {
        fun responseProductsList(category: String,key: String,user_id:String)
    }
}