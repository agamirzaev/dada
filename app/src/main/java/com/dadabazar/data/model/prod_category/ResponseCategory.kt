package com.dadabazar.data.model.prod_category

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ResponseCategory {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("catg_id")
    @Expose
    private var catgId: String? = null

    @SerializedName("catg_name")
    @Expose
    private var catgName: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("sort_order")
    @Expose
    private var sortOrder: String? = null

    @SerializedName("catg_parent")
    @Expose
    private var catgParent: String? = null

    @SerializedName("level")
    @Expose
    private var level: String? = null

    @SerializedName("has_child")
    @Expose
    private var hasChild: String? = null

    @SerializedName("page")
    @Expose
    private var page: String? = null

    @SerializedName("for_seller")
    @Expose
    private var forSeller: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getCatgId(): String? {
        return catgId
    }

    fun setCatgId(catgId: String?) {
        this.catgId = catgId
    }

    fun getCatgName(): String? {
        return catgName
    }

    fun setCatgName(catgName: String?) {
        this.catgName = catgName
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getSortOrder(): String? {
        return sortOrder
    }

    fun setSortOrder(sortOrder: String?) {
        this.sortOrder = sortOrder
    }

    fun getCatgParent(): String? {
        return catgParent
    }

    fun setCatgParent(catgParent: String?) {
        this.catgParent = catgParent
    }

    fun getLevel(): String? {
        return level
    }

    fun setLevel(level: String?) {
        this.level = level
    }

    fun getHasChild(): String? {
        return hasChild
    }

    fun setHasChild(hasChild: String?) {
        this.hasChild = hasChild
    }

    fun getPage(): String? {
        return page
    }

    fun setPage(page: String?) {
        this.page = page
    }

    fun getForSeller(): String? {
        return forSeller
    }

    fun setForSeller(forSeller: String?) {
        this.forSeller = forSeller
    }
}