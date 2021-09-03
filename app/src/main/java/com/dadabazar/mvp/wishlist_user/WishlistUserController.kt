package com.dadabazar.mvp.wishlist_user

import com.dadabazar.data.model.wishlist.Wishlist
import com.dadabazar.mvp.MvpView

interface WishlistUserController : MvpView {
    interface View : MvpView {
        fun getWishlist(wishlist: ArrayList<Wishlist>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notWishlist()
    }

    interface Presenter : MvpView {
        fun responseWishlist(user_id: String,key: String)
    }
}