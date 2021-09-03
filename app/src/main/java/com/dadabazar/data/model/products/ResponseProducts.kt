package com.dadabazar.data.model.products

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResponseProducts {
    @SerializedName("response")
    @Expose
    private var response: ArrayList<ProductsList>? = null

    fun getResponse(): ArrayList<ProductsList>? {
        return response
    }

    fun setResponse(response: ArrayList<ProductsList>?) {
        this.response = response
    }
}