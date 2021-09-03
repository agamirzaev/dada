package com.dadabazar.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterProductsProfileSeller
import com.dadabazar.adapter.AdapterProductsUser
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.profile_user.ProfileUserControler
import com.dadabazar.mvp.profile_user.ProfileUserPresenter
import com.dadabazar.utills.Preferences
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@Suppress("SENSELESS_COMPARISON")
class ProfileUserFragment : Fragment(), ProfileUserControler.View {
    private lateinit var presenter: ProfileUserPresenter
    private lateinit var nameUserProfile: TextView
    private lateinit var namesUserProfile: TextView
    private lateinit var avatarUserProfile: CircleImageView
    private lateinit var locationUserProfile: TextView
    private lateinit var constraintLayoutView: ConstraintLayout
    private lateinit var notInternetProfile: TextView
    private lateinit var progressBarProfile: ProgressBar
    private lateinit var recyclerViewProductsUser: RecyclerView
    private lateinit var constraintLayoutNotBasket: ConstraintLayout

    private lateinit var btnBackCatalog: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_user, container, false)

        notInternetProfile = view.findViewById(R.id.notInternetProfile)
        progressBarProfile = view.findViewById(R.id.progressBarProfile)

        nameUserProfile = view.findViewById(R.id.nameUserProfile)
        namesUserProfile = view.findViewById(R.id.namesUserProfile)
        avatarUserProfile = view.findViewById(R.id.avatarUserProfile)
        locationUserProfile = view.findViewById(R.id.locationUserProfile)
        constraintLayoutView = view.findViewById(R.id.constraintLayoutView)
        recyclerViewProductsUser = view.findViewById(R.id.recyclerViewProductsUser)
        constraintLayoutNotBasket = view.findViewById(R.id.constraintLayoutNotProductsUser)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)

        presenter = ProfileUserPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@ProfileUserFragment)
        presenter.responseUsers(Preferences.loadUserId(requireContext()).toString(),"2AOmTW")
        presenter.responseUsersProducts(Preferences.loadUserId(requireContext()).toString(),"2AOmTW")

        val mGridLayoutManager = GridLayoutManager(view.context, 2)
        recyclerViewProductsUser.layoutManager = mGridLayoutManager

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).backFragmentMain()
        }
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun getUserShow(user: Users) {
        if (user.getResponse() != null) {
            nameUserProfile.text =
                user.getResponse()!!.getFname() + " " + user.getResponse()!!.getLname()
            namesUserProfile.text =
                user.getResponse()!!.getFname() + " " + user.getResponse()!!.getLname()
            Picasso.get().load("https://dadabazar.online/" + user.getResponse()!!.getAvatar())
                .into(avatarUserProfile)
            if (user.getResponse()!!.getCity() != "") {
                locationUserProfile.text = user.getResponse()!!.getCity()
            } else {
                locationUserProfile.text = "Адрес пользователя неизвестен"
            }
        }
    }

    override fun getProductsShow(productsList: ArrayList<ProductsList>) {
        val myAdapter = AdapterProductsUser(productsList)
        if (myAdapter != null) {
            recyclerViewProductsUser.adapter = myAdapter
        } else {
            constraintLayoutNotBasket.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        if (Preferences.loadUserId(requireContext()).toString() != "") {
            (activity as MainActivity).basketCount(0)
        }
        super.onStart()
    }

    override fun onResume() {
        if (Preferences.loadUserId(requireContext()).toString() != "") {
            (activity as MainActivity).basketCount(0)
        }
        super.onResume()
    }

    override fun showProgress() {
        progressBarProfile.visibility = View.VISIBLE
        constraintLayoutView.visibility = View.GONE
        constraintLayoutNotBasket.visibility = View.GONE
    }

    override fun hideProgress() {
        notInternetProfile.visibility = View.GONE
        progressBarProfile.visibility = View.GONE
        constraintLayoutNotBasket.visibility = View.GONE
        constraintLayoutView.visibility = View.VISIBLE
    }

    override fun noConnection() {
        notInternetProfile.visibility = View.VISIBLE
        constraintLayoutView.visibility = View.GONE
        constraintLayoutNotBasket.visibility = View.GONE
    }

    override fun notProductsList() {
        constraintLayoutNotBasket.visibility = View.VISIBLE
    }
}