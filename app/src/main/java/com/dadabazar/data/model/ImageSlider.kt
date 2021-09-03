package com.dadabazar.data.model

class ImageSlider(private var imageUrl: String) {

    private var flagResource = imageUrl


    fun getImageUrl(): String {
        return flagResource
    }

    fun setImageUrl(image: String?) {
        this.imageUrl = image!!
    }
}