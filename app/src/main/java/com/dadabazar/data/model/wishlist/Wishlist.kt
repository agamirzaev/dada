package com.dadabazar.data.model.wishlist

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Wishlist {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("poster")
    @Expose
    private var poster: String? = null

    @SerializedName("thumb")
    @Expose
    private var thumb: String? = null

    @SerializedName("sale_price")
    @Expose
    private var salePrice: String? = null

    @SerializedName("reg_price")
    @Expose
    private var regPrice: String? = null

    @SerializedName("rating")
    @Expose
    private var rating: String? = null

    @SerializedName("isWishlist")
    @Expose
    private var isWishlist: String? = null

    @SerializedName("isBasket")
    @Expose
    private var isBasket: String? = null


    fun getIsWishlist(): String? {
        return isWishlist
    }

    fun getIsBasket(): String? {
        return isBasket
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPoster(): String? {
        return poster
    }

    fun setPoster(poster: String?) {
        this.poster = poster
    }

    fun getThumb(): String? {
        return thumb
    }

    fun setThumb(thumb: String?) {
        this.thumb = thumb
    }

    fun getSalePrice(): String? {
        return salePrice
    }

    fun setSalePrice(salePrice: String?) {
        this.salePrice = salePrice
    }

    fun getRegPrice(): String? {
        return regPrice
    }

    fun setRegPrice(regPrice: String?) {
        this.regPrice = regPrice
    }

    fun getRating(): String? {
        return rating
    }

    fun setRating(rating: String?) {
        this.rating = rating
    }
}