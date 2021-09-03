package com.dadabazar.data.model.searchProducts

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ProductsSearch {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("poster")
    @Expose
    private var poster: String? = null

    @SerializedName("sale_price")
    @Expose
    private var salePrice: String? = null

    @SerializedName("reg_price")
    @Expose
    private var regPrice: String? = null

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

}