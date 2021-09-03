package com.dadabazar.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.R
import com.dadabazar.data.model.prod_category.ProdCategory
import com.dadabazar.data.model.prod_category.ResponseCategory
import com.dadabazar.fragment.DetailsCatalogFragment
import com.dadabazar.fragment.ProductsFragment

class AdapterDetailsCatalog(private val category: ArrayList<ResponseCategory?>) :
    RecyclerView.Adapter<AdapterDetailsCatalog.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDetailsCatalog.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterDetailsCatalog.ViewHolder, position: Int) {
        val category: ResponseCategory = category[position]!!
        viewHolder.bind(holder, category)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameCategory: TextView = view.findViewById<View>(R.id.name_products) as TextView

        fun bind(holder: ViewHolder, category: ResponseCategory) {

            holder.nameCategory.text = category.getCatgName().toString()

            if (category.getHasChild() == "Y") {
                itemView.setOnClickListener {
                    val fragment: Fragment = DetailsCatalogFragment()
                    val bundle = Bundle()
                    bundle.putString("CatgName", category.getCatgName().toString())
                    fragment.arguments = bundle
                    val fragManager: FragmentManager =
                        (itemView.context as AppCompatActivity).supportFragmentManager
                    val ft: FragmentTransaction = fragManager.beginTransaction()
                    ft.replace(R.id.content, fragment)
                    ft.commit()
                }
            } else {
                itemView.setOnClickListener {
                    val fragment: Fragment = ProductsFragment()
                    val bundle = Bundle()

                    bundle.putString("CatgId", category.getCatgId().toString())
                    bundle.putString("CatgName", category.getCatgName().toString())
                    fragment.arguments = bundle
                    val fragManager: FragmentManager =
                        (itemView.context as AppCompatActivity).supportFragmentManager
                    val ft: FragmentTransaction = fragManager.beginTransaction()
                    ft.replace(R.id.content, fragment)
                    ft.commit()
                }
            }
        }
    }
}