package com.dadabazar.mvp.wishlist_del

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.wishlist.DelWishlist
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistDelPresenter(private val dataManager: DataManager) :
    BasePresenter<WishlistDelController.View>(),
    WishlistDelController.Presenter {

    private lateinit var call: Call<DelWishlist>

    override fun responseDelWishlist(key: String, user_id: String, prod_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getDelWishlist(key, user_id, prod_id)
            call.enqueue(object : Callback<DelWishlist> {

                override fun onResponse(
                    call: Call<DelWishlist>,
                    response: Response<DelWishlist>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getDelWishlist(res)
                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<DelWishlist>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }
}