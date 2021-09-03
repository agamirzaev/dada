package com.dadabazar.data.model

import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("id")
    private var id: Int? = null

    @SerializedName("catg_id")
    private var catgId: String? = null

    @SerializedName("catg_name")
    private var catgName: String? = null

    @SerializedName("status")
    private var status: String? = null

    @SerializedName("sort_order")
    private var sortOrder: Int? = null

    @SerializedName("catg_parent")
    private var catgParent: String? = null

    @SerializedName("level")
    private var level: Int? = null

    @SerializedName("has_child")
    private var hasChild: String? = null

    @SerializedName("page")
    private var page: String? = null

    @SerializedName("for_seller")
    private var forSeller: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
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

    fun getSortOrder(): Int? {
        return sortOrder
    }

    fun setSortOrder(sortOrder: Int?) {
        this.sortOrder = sortOrder
    }

    fun getCatgParent(): String? {
        return catgParent
    }

    fun setCatgParent(catgParent: String?) {
        this.catgParent = catgParent
    }

    fun getLevel(): Int? {
        return level
    }

    fun setLevel(level: Int?) {
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