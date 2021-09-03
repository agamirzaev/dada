package com.dadabazar.mvp.profile_user

import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.MvpView

interface ProfileUserControler : MvpView {
    interface View : MvpView {
        fun getUserShow(user: Users)
        fun getProductsShow(productsList: ArrayList<ProductsList>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notProductsList()
    }

    interface Presenter : MvpView {
        fun responseUsers(user_id: String, key: String)
        fun responseUsersProducts(user_id: String, key: String)
    }
}