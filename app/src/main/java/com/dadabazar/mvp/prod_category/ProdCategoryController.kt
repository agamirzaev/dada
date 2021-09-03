package com.dadabazar.mvp.prod_category

import com.dadabazar.data.model.prod_category.ResponseCategory
import com.dadabazar.mvp.MvpView

interface ProdCategoryController : MvpView {
    interface View : MvpView {
        fun getProdCategory(category: ArrayList<ResponseCategory?>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun noCategoryProducts()
    }

    interface Presenter : MvpView {
        fun responseProdCategory(catg_parent: String,key: String)
    }
}