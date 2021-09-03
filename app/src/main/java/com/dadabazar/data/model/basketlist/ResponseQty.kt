package com.dadabazar.data.model.basketlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseQty {
    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null


    @SerializedName("quentity")
    @Expose
    private var quentity: String? = null

    fun getQuentity(): String? {
        return quentity
    }

    fun getStatus(): String? {
        return status
    }

    fun getMessage(): String? {
        return message
    }
}