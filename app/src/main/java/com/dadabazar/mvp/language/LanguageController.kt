package com.dadabazar.mvp.language

import com.dadabazar.data.model.Language
import com.dadabazar.mvp.MvpView

interface LanguageController : MvpView {
    interface View : MvpView {
        fun getLanguage(language: ArrayList<Language>)
    }

    interface Presenter : MvpView {
        fun responseLanguage()
    }
}