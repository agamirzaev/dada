package com.dadabazar.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Users {

    @SerializedName("response")
    @Expose
    private var response: ResponseUser? = null

    fun getResponse(): ResponseUser? {
        return response
    }

    fun setResponse(response: ResponseUser?) {
        this.response = response
    }
}