package com.dadabazar.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRegister {

    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("group_id")
    @Expose
    private var groupId: Int? = null

    fun getGroupId(): Int? {
        return groupId
    }

    fun setGroupId(groupId: Int) {
        this.groupId = groupId
    }

    fun getStatus(): Boolean? {
        return status
    }

    fun setStatus(status: Boolean?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }
}