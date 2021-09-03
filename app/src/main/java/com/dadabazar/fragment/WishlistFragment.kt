package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterWishlist
import com.dadabazar.data.model.wishlist.Wishlist
import com.dadabazar.mvp.wishlist_user.WishlistUserController
import com.dadabazar.mvp.wishlist_user.WishlistUserPresenter
import com.dadabazar.utills.Preferences

class WishlistFragment : Fragment(), WishlistUserController.View {

    private lateinit var wishlistUserPresenter: WishlistUserPresenter
    private lateinit var recyclerViewBookMarks: RecyclerView
    private lateinit var btnBackCatalogBookMarks: ImageView
    private lateinit var notInternetBasket: TextView
    private lateinit var progressBarBasket: ProgressBar
    private lateinit var nestedScrollViewBasket: NestedScrollView
    private lateinit var notWishlistUser: RelativeLayout
    private lateinit var clickMainFragment: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_marks, container, false)

        recyclerViewBookMarks = view.findViewById(R.id.recyclerViewBookMarks)
        btnBackCatalogBookMarks = view.findViewById(R.id.btnBackCatalogBookMarks)
        notInternetBasket = view.findViewById(R.id.notInternetBookMarks)
        progressBarBasket = view.findViewById(R.id.progressBarBookMarks)
        nestedScrollViewBasket = view.findViewById(R.id.nestedScrollViewBookMarks)
        notWishlistUser = view.findViewById(R.id.notWishlistUser)
        clickMainFragment = view.findViewById(R.id.clickMainFragment)

        wishlistUserPresenter =
            WishlistUserPresenter((view.context.applicationContext as App).dataManager!!)
        wishlistUserPresenter.attachView(this@WishlistFragment)
        wishlistUserPresenter.responseWishlist("" + Preferences.loadUserId(view.context),"2AOmTW")

        btnBackCatalogBookMarks.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@WishlistFragment)
                .commit()
            (activity as MainActivity).backFragmentCatalog()
        }
        val mGridLayoutManager = GridLayoutManager(view.context, 2)
        recyclerViewBookMarks.layoutManager = mGridLayoutManager

        clickMainFragment.setOnClickListener {
            (activity as MainActivity).backFragmentMain()
        }

        return view
    }

    @Suppress("SENSELESS_COMPARISON")
    override fun getWishlist(wishlist: ArrayList<Wishlist>) {
        val myAdapter = AdapterWishlist(wishlist, this@WishlistFragment)
        if (myAdapter != null) {
            recyclerViewBookMarks.adapter = myAdapter
        }
    }

    override fun showProgress() {
        progressBarBasket.visibility = View.VISIBLE
        nestedScrollViewBasket.visibility = View.GONE
    }

    override fun hideProgress() {
        nestedScrollViewBasket.visibility = View.VISIBLE
        progressBarBasket.visibility = View.GONE
    }

    override fun noConnection() {
        nestedScrollViewBasket.visibility = View.GONE
        notInternetBasket.visibility = View.VISIBLE
        progressBarBasket.visibility = View.GONE
    }

    override fun notWishlist() {
        notWishlistUser.visibility = View.VISIBLE
    }

    fun notWishlistProducts() {
        notWishlistUser.visibility = View.VISIBLE
    }

}