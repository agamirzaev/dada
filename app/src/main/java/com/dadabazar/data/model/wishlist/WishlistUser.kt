package com.dadabazar.data.model.wishlist

import com.dadabazar.data.model.wishlist.Wishlist
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class WishlistUser {
    @SerializedName("response")
    @Expose
    private var response: ArrayList<Wishlist>? = null

    fun getResponse(): ArrayList<Wishlist>? {
        return response
    }

    fun setResponse(response: ArrayList<Wishlist>?) {
        this.response = response
    }
}