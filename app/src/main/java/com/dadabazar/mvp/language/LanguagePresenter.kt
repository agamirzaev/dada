package com.dadabazar.mvp.language

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.Language
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguagePresenter(private val dataManager: DataManager) :
    BasePresenter<LanguageController.View>(),
    LanguageController.Presenter {

    private lateinit var callLanguage: Call<ArrayList<Language>>

    override fun responseLanguage() {
        mvpView.let {
            callLanguage = dataManager.getLanguageList()
            callLanguage.enqueue(object : Callback<ArrayList<Language>> {

                override fun onResponse(
                    call: Call<ArrayList<Language>>,
                    response: Response<ArrayList<Language>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { _ ->
                            it?.getLanguage(response.body()!!)
                        }
                    }
                    Log.e("SUCCESSLanguage", call.request().toString())
                }

                override fun onFailure(call: Call<ArrayList<Language>>, t: Throwable) {
                    Log.e(" ERRORLanguage", call.request().toString())
                }
            })
        }
    }
}