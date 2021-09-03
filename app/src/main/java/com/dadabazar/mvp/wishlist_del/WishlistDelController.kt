package com.dadabazar.mvp.wishlist_del

import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.data.model.wishlist.DelWishlist
import com.dadabazar.mvp.MvpView

interface WishlistDelController : MvpView {
    interface View : MvpView {
        fun getDelWishlist(delWishlist: DelWishlist)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseDelWishlist(key: String,user_id: String, prod_id: String)
    }
}