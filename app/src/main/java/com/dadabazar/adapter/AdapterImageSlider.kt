package com.dadabazar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dadabazar.R
import com.dadabazar.data.model.ImageSlider
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class AdapterImageSlider(private var imageSlider: ArrayList<ImageSlider>) :
    SliderViewAdapter<AdapterImageSlider.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onBindViewHolder(holder: AdapterImageSlider.ViewHolder, position: Int) {
        val image = imageSlider[position]
        holder.bind(holder, image)
    }

    inner class ViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView? = null

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_image_slider)
        }

        fun bind(holder: ViewHolder, image: ImageSlider) {
            viewHolder.imageViewBackground?.let {
                Picasso.get()
                    .load(image.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(holder.imageViewBackground)
            }
        }
    }

    override fun getCount(): Int {
        return imageSlider.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater
            .from(parent!!.context)
            .inflate(R.layout.item_image_slider, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }
}