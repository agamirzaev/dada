package com.dadabazar.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Checked {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    fun getStatus(): Boolean? {
        return status
    }
}