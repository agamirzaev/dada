package com.dadabazar.data.remote

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
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("prod_categories")
    fun getCategoryProducts(
        @Query("key") key: String
    ): Call<ProdCategory>

    @FormUrlEncoded
    @POST("login")
    fun getLoginUser(
        @Field("key") key: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserLogin>

    @FormUrlEncoded
    @POST("register")
    fun getRegisterUser(
        @Field("key") key: String,
        @Field("fname") fname: String,
        @Field("lname") lname: String,
        @Field("uname") uname: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("conf_pass") conf_pass: String
    ): Call<UserRegister>

    @GET("prod_category")
    fun getCategoryProductsParent(
        @Query("catg_parent") catg_parent: String,
        @Query("key") key: String
    ): Call<ProdCategory>

    @GET("search_product")
    fun getSearchProduct(
        @Query("key") key: String,
        @Query("prod_name") name: String
    ): Call<ResponseSearch>

    @GET("products")
    fun getCategoryProductsList(
        @Query("category") category: String,
        @Query("key") key: String,
        @Query("user_id") user_id: String
    ): Call<ResponseProducts>

    @GET("product")
    fun getProductId(
        @Query("id_product") id_product: String,
        @Query("key") key: String,
        @Query("user_id") user_id: String
    ): Call<ResponseProducts>

    @GET("products_user")
    fun getProductProfileSellerList(
        @Query("key") key: String,
        @Query("user_id") user_id: String,
        @Query("current_id") current_id: String
    ): Call<ResponseProducts>

    @GET("products_user")
    fun getProductListProfile(
        @Query("user_id") user_id: String,
        @Query("key") key: String
    ): Call<ResponseProducts>

    @GET("products_user")
    fun getProductTrands(
        @Query("key") key: String,
        @Query("user_id") user_id: String
    ): Call<ResponseProducts>

    @GET("users")
    fun getUser(
        @Query("id") user_id: String,
        @Query("key") key: String
    ): Call<Users>

    @GET("wishlist_all")
    fun getWishlistUser(
        @Query("user_id") user_id: String,
        @Query("key") key: String
    ): Call<WishlistUser>

    @GET("basket_all")
    fun getBasketListUser(
        @Query("key") key: String,
        @Query("user_id") user_id: String
    ): Call<ResponseBasket>

    @FormUrlEncoded
    @POST("wishlist_add")
    fun getWishlistAddUser(
        @Field("key") key: String,
        @Field("user_id") user_id: String,
        @Field("prod_id") prod_id: String
    ): Call<AddWishlist>

    @FormUrlEncoded
    @POST("wishlist_del")
    fun getWishlistDeleteUser(
        @Field("key") key: String,
        @Field("user_id") user_id: String,
        @Field("prod_id") prod_id: String
    ): Call<DelWishlist>

    @FormUrlEncoded
    @POST("basket_add")
    fun getBasketAddUser(
        @Field("key") key: String,
        @Field("user_id") user_id: String,
        @Field("prod_id") prod_id: String,
        @Field("quantity") quantity: Int
    ): Call<AddBasket>

    @FormUrlEncoded
    @POST("basket_del")
    fun getBasketDeleteUser(
        @Field("key") key: String,
        @Field("user_id") user_id: String,
        @Field("prod_id") prod_id: String
    ): Call<DelBasket>

    @FormUrlEncoded
    @POST("checked")
    fun getChecked(
        @Field("key") key: String,
        @Field("user_id") user_id: String,
        @Field("prod_id") prod_id: String
    ): Call<Checked>


    @FormUrlEncoded
    @POST("update_qty")
    fun getUpdateQty(
        @Field("key") key: String,
        @Field("user_id") user_id: String,
        @Field("prod_id") prod_id: String,
        @Field("action") action: String
    ): Call<ResponseQty>

    @GET("langs")
    fun getLanguage(): Call<ArrayList<Language>>
}