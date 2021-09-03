package com.dadabazar.data

import com.dadabazar.data.model.*
import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.data.model.basketlist.DelBasket
import com.dadabazar.data.model.basketlist.ResponseBasket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.data.model.prod_category.ProdCategory
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.data.model.searchProducts.ResponseSearch
import com.dadabazar.data.model.wishlist.WishlistUser
import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.data.model.wishlist.DelWishlist
import com.dadabazar.data.remote.Api
import com.dadabazar.data.remote.ServicesGenerator
import retrofit2.Call
import retrofit2.http.Query

class DataManager {
    private val api = ServicesGenerator.createService(Api::class.java)

    fun getCategoryList(key: String): Call<ProdCategory> {
        return api.getCategoryProducts(key)
    }

    fun getLoginUser(key: String, email: String, password: String): Call<UserLogin> {
        return api.getLoginUser(key, email, password)
    }

    fun getRegisterUser(
        key: String,
        fname: String,
        lname: String,
        uname: String,
        email: String,
        password: String,
        conf_pass: String
    ): Call<UserRegister> {
        return api.getRegisterUser(key, fname, lname, uname, email, password, conf_pass)
    }

    fun getPodCategoryList(catg_parent: String, key: String): Call<ProdCategory> {
        return api.getCategoryProductsParent(catg_parent, key)
    }

    fun getSearchProductsList(key: String, name: String): Call<ResponseSearch> {
        return api.getSearchProduct(key, name)
    }

    fun getWishlist(user_id: String, key: String): Call<WishlistUser> {
        return api.getWishlistUser(user_id, key)
    }

    fun getBasketList(key: String, user_id: String): Call<ResponseBasket> {
        return api.getBasketListUser(key, user_id)
    }

    fun getAddWishlist(key: String, user_id: String, prod_id: String): Call<AddWishlist> {
        return api.getWishlistAddUser(key, user_id, prod_id)
    }

    fun getDelWishlist(key: String, user_id: String, prod_id: String): Call<DelWishlist> {
        return api.getWishlistDeleteUser(key, user_id, prod_id)
    }

    fun getAddBasketList(
        key: String,
        user_id: String,
        prod_id: String,
        quantity: Int
    ): Call<AddBasket> {
        return api.getBasketAddUser(key, user_id, prod_id, quantity)
    }

    fun getDelBasketList(key: String, user_id: String, prod_id: String): Call<DelBasket> {
        return api.getBasketDeleteUser(key, user_id, prod_id)
    }

    fun getProductsTrands(key: String, user_id: String): Call<ResponseProducts> {
        return api.getProductTrands(key, user_id)
    }

    fun getProductProfileSeller(
        key: String,
        user_id: String,
        current_id: String
    ): Call<ResponseProducts> {
        return api.getProductProfileSellerList(key, user_id, current_id)
    }

    fun getProductProfile(user_id: String, key: String): Call<ResponseProducts> {
        return api.getProductListProfile(user_id, key)
    }

    fun getProductsList(catygory: String, key: String, user_id: String): Call<ResponseProducts> {
        return api.getCategoryProductsList(catygory, key, user_id)
    }

    fun getProductId(id_product: String, key: String, user_id: String): Call<ResponseProducts> {
        return api.getProductId(id_product, key, user_id)
    }

    fun getUser(user_id: String, key: String): Call<Users> {
        return api.getUser(user_id, key)
    }

    fun getChecked(key: String, user_id: String, prod_id: String): Call<Checked> {
        return api.getChecked(key, user_id, prod_id)
    }

    fun getUpdateQty(
        key: String,
        user_id: String,
        prod_id: String,
        action: String
    ): Call<ResponseQty> {
        return api.getUpdateQty(key, user_id, prod_id, action)
    }

    fun getLanguageList(): Call<ArrayList<Language>> {
        return api.getLanguage()
    }

}