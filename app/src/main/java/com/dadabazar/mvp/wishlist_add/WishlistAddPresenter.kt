package com.dadabazar.mvp.wishlist_add

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.wishlist.AddWishlist
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistAddPresenter(private val dataManager: DataManager) :
    BasePresenter<WishlistAddController.View>(),
    WishlistAddController.Presenter {

    private lateinit var call: Call<AddWishlist>

    override fun responseAddWishlist(key: String,user_id: String, prod_id: String) {
        mvpView.let { it ->
            call = dataManager.getAddWishlist(key,user_id, prod_id)
            call.enqueue(object : Callback<AddWishlist> {

                override fun onResponse(
                    call: Call<AddWishlist>,
                    response: Response<AddWishlist>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getAddWishlist(res)
                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<AddWishlist>, t: Throwable) {
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }
}