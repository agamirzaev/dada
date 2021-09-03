package com.dadabazar.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Language {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("lang_key")
    @Expose
    private var langKey: String? = null

    @SerializedName("english")
    @Expose
    private var english: String? = null

    @SerializedName("italian")
    @Expose
    private var italian: String? = null

    @SerializedName("russian")
    @Expose
    private var russian: String? = null

    @SerializedName("spanish")
    @Expose
    private var spanish: String? = null

    @SerializedName("turkish")
    @Expose
    private var turkish: String? = null

    @SerializedName("dutch")
    @Expose
    private var dutch: String? = null

    @SerializedName("ukraine")
    @Expose
    private var ukraine: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getLangKey(): String? {
        return langKey
    }

    fun setLangKey(langKey: String?) {
        this.langKey = langKey
    }

    fun getEnglish(): String? {
        return english
    }

    fun setEnglish(english: String?) {
        this.english = english
    }

    fun getItalian(): String? {
        return italian
    }

    fun setItalian(italian: String?) {
        this.italian = italian
    }

    fun getRussian(): String? {
        return russian
    }

    fun setRussian(russian: String?) {
        this.russian = russian
    }

    fun getSpanish(): String? {
        return spanish
    }

    fun setSpanish(spanish: String?) {
        this.spanish = spanish
    }

    fun getTurkish(): String? {
        return turkish
    }

    fun setTurkish(turkish: String?) {
        this.turkish = turkish
    }

    fun getDutch(): String? {
        return dutch
    }

    fun setDutch(dutch: String?) {
        this.dutch = dutch
    }

    fun getUkraine(): String? {
        return ukraine
    }

    fun setUkraine(ukraine: String?) {
        this.ukraine = ukraine
    }

}