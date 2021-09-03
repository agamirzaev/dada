package com.dadabazar.data.model.searchProducts

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ResponseSearch {
    @SerializedName("response")
    @Expose
    private var response: ArrayList<ProductsSearch>? = null

    fun getResponse(): ArrayList<ProductsSearch>? {
        return response
    }

    fun setResponse(response: ArrayList<ProductsSearch>?) {
        this.response = response
    }
}