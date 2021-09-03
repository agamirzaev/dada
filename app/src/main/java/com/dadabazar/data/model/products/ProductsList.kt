package com.dadabazar.data.model.products

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ProductsList {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("user_id")
    @Expose
    private var userId: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("keywords")
    @Expose
    private var keywords: String? = null

    @SerializedName("category")
    @Expose
    private var category: String? = null

    @SerializedName("condition")
    @Expose
    private var condition: String? = null

    @SerializedName("has_variations")
    @Expose
    private var hasVariations: String? = null

    @SerializedName("reg_price")
    @Expose
    private var regPrice: String? = null

    @SerializedName("sale_price")
    @Expose
    private var salePrice: String? = null

    @SerializedName("quantity")
    @Expose
    private var quantity: String? = null

    @SerializedName("rating")
    @Expose
    private var rating: String? = null

    @SerializedName("poster")
    @Expose
    private var poster: String? = null

    @SerializedName("thumb")
    @Expose
    private var thumb: String? = null

    @SerializedName("variation_type")
    @Expose
    private var variationType: String? = null

    @SerializedName("isWishlist")
    @Expose
    private var isWishlist: String? = null

    @SerializedName("isBasket")
    @Expose
    private var isBasket: String? = null

    @SerializedName("qty_max")
    @Expose
    private var qtyMax: String? = null


    fun getQtyMax(): String? {
        return qtyMax
    }


    fun getIsWishlist(): String? {
        return isWishlist
    }

    fun getIsBasket(): String? {
        return isBasket
    }

    fun getVariationType(): String? {
        return variationType
    }

    fun getId(): String? {
        return id
    }

    fun getUserId(): String? {
        return userId
    }

    fun getPoster(): String? {
        return poster
    }

    fun getThunb(): String? {
        return thumb
    }


    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getKeywords(): String? {
        return keywords
    }

    fun setKeywords(keywords: String?) {
        this.keywords = keywords
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun getCondition(): String? {
        return condition
    }

    fun setCondition(condition: String?) {
        this.condition = condition
    }

    fun getHasVariations(): String? {
        return hasVariations
    }

    fun setHasVariations(hasVariations: String?) {
        this.hasVariations = hasVariations
    }

    fun getRegPrice(): String? {
        return regPrice
    }

    fun setRegPrice(regPrice: String?) {
        this.regPrice = regPrice
    }

    fun getSalePrice(): String? {
        return salePrice
    }

    fun setSalePrice(salePrice: String?) {
        this.salePrice = salePrice
    }

    fun getQuantity(): String? {
        return quantity
    }

    fun setQuantity(quantity: String?) {
        this.quantity = quantity
    }

    fun getRating(): String? {
        return rating
    }

    fun setRating(rating: String?) {
        this.rating = rating
    }
}