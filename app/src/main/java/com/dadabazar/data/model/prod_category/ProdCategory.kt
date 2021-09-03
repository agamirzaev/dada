package com.dadabazar.data.model.prod_category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProdCategory {

    @SerializedName("response")
    @Expose
    private var response: ArrayList<ResponseCategory?>? = null

    fun getResponse(): ArrayList<ResponseCategory?>? {
        return response
    }

    fun setResponse(response: ArrayList<ResponseCategory?>?) {
        this.response = response
    }
}