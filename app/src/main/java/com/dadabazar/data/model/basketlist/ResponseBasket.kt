package com.dadabazar.data.model.basketlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseBasket {
    @SerializedName("response")
    @Expose
    private var response: ArrayList<Basket>? = null

    fun getResponseBasket(): ArrayList<Basket>? {
        return response
    }

    fun setResponseBasket(response: ArrayList<Basket>?) {
        this.response = response
    }
}