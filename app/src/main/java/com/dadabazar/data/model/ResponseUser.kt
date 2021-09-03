package com.dadabazar.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ResponseUser {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("fname")
    @Expose
    private var fname: String? = null

    @SerializedName("lname")
    @Expose
    private var lname: String? = null

    @SerializedName("bio")
    @Expose
    private var bio: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("avatar")
    @Expose
    private var avatar: String? = null

    @SerializedName("language")
    @Expose
    private var language: String? = null

    @SerializedName("country_id")
    @Expose
    private var countryId: String? = null

    @SerializedName("youtube")
    @Expose
    private var youtube: String? = null

    @SerializedName("facebook")
    @Expose
    private var facebook: String? = null

    @SerializedName("instagram")
    @Expose
    private var instagram: String? = null

    @SerializedName("google_plus")
    @Expose
    private var googlePlus: String? = null

    @SerializedName("phone")
    @Expose
    private var phone: String? = null

    @SerializedName("whatsapp")
    @Expose
    private var whatsapp: String? = null

    @SerializedName("state")
    @Expose
    private var state: String? = null

    @SerializedName("city")
    @Expose
    private var city: String? = null

    @SerializedName("street")
    @Expose
    private var street: String? = null

    @SerializedName("wallet")
    @Expose
    private var wallet: String? = null

    @SerializedName("active")
    @Expose
    private var active: String? = null

    @SerializedName("admin")
    @Expose
    private var admin: String? = null

    @SerializedName("sales")
    @Expose
    private var sales: String? = null

    @SerializedName("verified")
    @Expose
    private var verified: String? = null

    @SerializedName("is_seller")
    @Expose
    private var isSeller: String? = null

    @SerializedName("is_provider")
    @Expose
    private var isProvider: String? = null

    @SerializedName("paid")
    @Expose
    private var paid: String? = null

    @SerializedName("deliv_addr")
    @Expose
    private var delivAddr: String? = null

    @SerializedName("zip_postal")
    @Expose
    private var zipPostal: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getFname(): String? {
        return fname
    }

    fun setFname(fname: String?) {
        this.fname = fname
    }

    fun getLname(): String? {
        return lname
    }

    fun setLname(lname: String?) {
        this.lname = lname
    }

    fun getBio(): String? {
        return bio
    }

    fun setBio(bio: String?) {
        this.bio = bio
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getAvatar(): String? {
        return avatar
    }

    fun setAvatar(avatar: String?) {
        this.avatar = avatar
    }

    fun getLanguage(): String? {
        return language
    }

    fun setLanguage(language: String?) {
        this.language = language
    }

    fun getCountryId(): String? {
        return countryId
    }

    fun setCountryId(countryId: String?) {
        this.countryId = countryId
    }

    fun getYoutube(): String? {
        return youtube
    }

    fun setYoutube(youtube: String?) {
        this.youtube = youtube
    }

    fun getFacebook(): String? {
        return facebook
    }

    fun setFacebook(facebook: String?) {
        this.facebook = facebook
    }

    fun getInstagram(): String? {
        return instagram
    }

    fun setInstagram(instagram: String?) {
        this.instagram = instagram
    }

    fun getGooglePlus(): String? {
        return googlePlus
    }

    fun setGooglePlus(googlePlus: String?) {
        this.googlePlus = googlePlus
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getWhatsapp(): String? {
        return whatsapp
    }

    fun setWhatsapp(whatsapp: String?) {
        this.whatsapp = whatsapp
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String?) {
        this.state = state
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String?) {
        this.city = city
    }

    fun getStreet(): String? {
        return street
    }

    fun setStreet(street: String?) {
        this.street = street
    }

    fun getWallet(): String? {
        return wallet
    }

    fun setWallet(wallet: String?) {
        this.wallet = wallet
    }

    fun getActive(): String? {
        return active
    }

    fun setActive(active: String?) {
        this.active = active
    }

    fun getAdmin(): String? {
        return admin
    }

    fun setAdmin(admin: String?) {
        this.admin = admin
    }

    fun getSales(): String? {
        return sales
    }

    fun setSales(sales: String?) {
        this.sales = sales
    }

    fun getVerified(): String? {
        return verified
    }

    fun setVerified(verified: String?) {
        this.verified = verified
    }

    fun getIsSeller(): String? {
        return isSeller
    }

    fun setIsSeller(isSeller: String?) {
        this.isSeller = isSeller
    }

    fun getIsProvider(): String? {
        return isProvider
    }

    fun setIsProvider(isProvider: String?) {
        this.isProvider = isProvider
    }

    fun getPaid(): String? {
        return paid
    }

    fun setPaid(paid: String?) {
        this.paid = paid
    }

    fun getDelivAddr(): String? {
        return delivAddr
    }

    fun setDelivAddr(delivAddr: String?) {
        this.delivAddr = delivAddr
    }

    fun getZipPostal(): String? {
        return zipPostal
    }

    fun setZipPostal(zipPostal: String?) {
        this.zipPostal = zipPostal
    }
}