package com.dadabazar.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterProductsProfileSeller
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.profile_seller_products.ProfileSellerProductsController
import com.dadabazar.mvp.profile_seller_products.ProfileSellerProductsPresenter
import com.dadabazar.utills.Preferences
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@Suppress("SENSELESS_COMPARISON")
class ProfileSellerFragment(private var user_id: String, private var productId: String) :
    Fragment(),
    ProfileSellerProductsController.View {
    private lateinit var recyclerViewProductsProfileSeller: RecyclerView
    private lateinit var btnBackCatalog: ImageView
    private lateinit var progressBarProductsProfileSeller: ProgressBar
    private lateinit var clickBtnShapeProfile: ImageView

    private lateinit var nameUserProfile: TextView
    private lateinit var namesUserProfile: TextView
    private lateinit var avatarUserProfile: CircleImageView
    private lateinit var locationUserProfile: TextView
    private lateinit var constraintLayoutNotBasket: ConstraintLayout
    private lateinit var nestedScrollViewSellerProfile: NestedScrollView

    private lateinit var notInternetProfileSeller: TextView
    private lateinit var countProductProfile: TextView

    private lateinit var presenter: ProfileSellerProductsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_seller, container, false)
        recyclerViewProductsProfileSeller =
            view.findViewById(R.id.recyclerViewProductsProfileSeller)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)
        notInternetProfileSeller = view.findViewById(R.id.notInternetProfileSeller)
        progressBarProductsProfileSeller = view.findViewById(R.id.progressBarProductsProfileSeller)
        clickBtnShapeProfile = view.findViewById(R.id.clickBtnShapeProfile)

        nameUserProfile = view.findViewById(R.id.nameUserProfileSeller)
        namesUserProfile = view.findViewById(R.id.namesUserProfileSeller)
        avatarUserProfile = view.findViewById(R.id.avatarUserProfileSeller)
        locationUserProfile = view.findViewById(R.id.locationUserProfileSeller)
        constraintLayoutNotBasket = view.findViewById(R.id.constraintLayoutNotProductsUserSeller)
        nestedScrollViewSellerProfile = view.findViewById(R.id.nestedScrollViewSellerProfile)
        countProductProfile = view.findViewById(R.id.countProductProfile)

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@ProfileSellerFragment)
                .commit()
            (activity as MainActivity).backDetailsCatalogFragment()
        }

        presenter =
            ProfileSellerProductsPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@ProfileSellerFragment)
        presenter.responseProductsProfileSeller(
            "2AOmTW",
            user_id,
            Preferences.loadUserId(requireContext()).toString()
        )
        presenter.responseProfileSeller(user_id, "2AOmTW")


        val mGridLayoutManager = GridLayoutManager(view.context, 2)
        recyclerViewProductsProfileSeller.layoutManager = mGridLayoutManager

        return view
    }

    override fun getProductsProfileSeller(productsList: ArrayList<ProductsList>) {
        countProductProfile.text = productsList.size.toString()
        val myAdapter = AdapterProductsProfileSeller(productsList)
        if (myAdapter != null) {
            recyclerViewProductsProfileSeller.adapter = myAdapter
        } else {
            constraintLayoutNotBasket.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getProfileSeller(users: Users) {
        if (users.getResponse() != null) {
            nameUserProfile.text =
                users.getResponse()!!.getFname() + " " + users.getResponse()!!.getLname()
            namesUserProfile.text =
                users.getResponse()!!.getFname() + " " + users.getResponse()!!.getLname()
            Picasso.get().load("https://dadabazar.online/" + users.getResponse()!!.getAvatar())
                .into(avatarUserProfile)
            if (users.getResponse()!!.getCity() != "") {
                locationUserProfile.text = users.getResponse()!!.getCity()
            } else {
                locationUserProfile.text = "Адрес пользователя неизвестен"
            }
        }
        clickBtnShapeProfile.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
            share.putExtra(
                Intent.EXTRA_TEXT,
                "https://dadabazar.online/${users.getResponse()!!.getUsername()}"
            )
            startActivity(Intent.createChooser(share, "Поделиться"))
        }
    }

    override fun showProgress() {
        nestedScrollViewSellerProfile.visibility = View.GONE
        progressBarProductsProfileSeller.visibility = View.VISIBLE
        constraintLayoutNotBasket.visibility = View.GONE
    }

    override fun hideProgress() {
        nestedScrollViewSellerProfile.visibility = View.VISIBLE
        progressBarProductsProfileSeller.visibility = View.GONE
        constraintLayoutNotBasket.visibility = View.GONE
    }

    override fun noConnection() {
        nestedScrollViewSellerProfile.visibility = View.GONE
        notInternetProfileSeller.visibility = View.VISIBLE
        constraintLayoutNotBasket.visibility = View.GONE
    }

    override fun nowProductsUserSeller() {
        constraintLayoutNotBasket.visibility = View.VISIBLE
    }
}