package com.dadabazar.mvp.wishlist_add

import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.mvp.MvpView

interface WishlistAddController : MvpView {
    interface View : MvpView {
        fun getAddWishlist(addWishlist: AddWishlist)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseAddWishlist(key: String, user_id: String, prod_id: String)
    }
}