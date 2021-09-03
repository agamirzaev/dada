package com.dadabazar.mvp.search_products

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.searchProducts.ResponseSearch
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchProductsPresenter(private val dataManager: DataManager) :
    BasePresenter<SearchProductsController.View>(),
    SearchProductsController.Presenter {

    private lateinit var call: Call<ResponseSearch>

    override fun responseProductsList(key: String, name: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getSearchProductsList(key, name)
            call.enqueue(object : Callback<ResponseSearch> {

                override fun onResponse(
                    call: Call<ResponseSearch>,
                    response: Response<ResponseSearch>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getProductsSearchList(res.getResponse()!!)
                            } else {
                                it?.noSearchProducs()
                            }
                        }
                    }
                    Log.e("ResponseSearch", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorResponseSearch", call.request().toString())
                }
            })
        }
    }
}