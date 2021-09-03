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
import com.dadabazar.data.model.Category
import com.dadabazar.data.model.prod_category.ResponseCategory
import com.dadabazar.fragment.DetailsCatalogFragment
import com.dadabazar.fragment.ProductsFragment

@Suppress("CAST_NEVER_SUCCEEDS")
class AdapterCategory(private val categoryes: ArrayList<ResponseCategory>) :
    RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategory.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterCategory.ViewHolder, position: Int) {
        val category: ResponseCategory = categoryes[position]
        viewHolder.bind(holder, category)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameCategory: TextView = view.findViewById<View>(R.id.name_products) as TextView

        fun bind(holder: ViewHolder, category: ResponseCategory) {

            holder.nameCategory.text = category.getCatgName().toString()

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
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryes.size
    }
}