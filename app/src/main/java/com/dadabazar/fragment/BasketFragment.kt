package com.dadabazar.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterBasket
import com.dadabazar.data.model.basketlist.Basket
import com.dadabazar.mvp.basket_list.BasketListController
import com.dadabazar.mvp.basket_list.BasketListPresenter
import com.dadabazar.utills.Preferences

@Suppress("SENSELESS_COMPARISON")
class BasketFragment : Fragment(), BasketListController.View {

    private lateinit var basketListPresenter: BasketListPresenter
    private lateinit var recyclerViewBasket: RecyclerView
    private lateinit var btnBackCatalog: ImageView
    private lateinit var notInternetBasket: TextView
    private lateinit var progressBarBasket: ProgressBar
    private lateinit var nestedScrollViewBasket: NestedScrollView
    private lateinit var countTotalPriceOrder: TextView
    private lateinit var titleCountBasket: TextView
    private lateinit var constraintLayoutNotBasket: ConstraintLayout
    private lateinit var constraintLayoutBaskerView: ConstraintLayout
    private lateinit var clickBtnOrder: AppCompatButton
    private lateinit var clickCheckBox: CheckBox
    private lateinit var clickRemoveAllBasket: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basket, container, false)
        recyclerViewBasket = view.findViewById(R.id.recyclerViewBasket)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)
        notInternetBasket = view.findViewById(R.id.notInternetBasket)
        progressBarBasket = view.findViewById(R.id.progressBarBasket)
        nestedScrollViewBasket = view.findViewById(R.id.nestedScrollViewBasket)
        countTotalPriceOrder = view.findViewById(R.id.countTotalPriceOrder)
        titleCountBasket = view.findViewById(R.id.titleCountBasket)
        constraintLayoutNotBasket = view.findViewById(R.id.ConstraintLayoutNotBasket)
        constraintLayoutBaskerView = view.findViewById(R.id.ConstraintLayoutBaskerView)
        clickBtnOrder = view.findViewById(R.id.clickBtnOrder)
        clickCheckBox = view.findViewById(R.id.clickCheckBox)
        clickRemoveAllBasket = view.findViewById(R.id.clickRemoveAllBasket)

        basketListPresenter =
            BasketListPresenter((view.context.applicationContext as App).dataManager!!)
        basketListPresenter.attachView(this@BasketFragment)
        basketListPresenter.responseBasketList(
            "2AOmTW",
            Preferences.loadUserId(view.context).toString(),
        )

        clickBtnOrder.setOnClickListener {
            loadFragment(MakingOrderFragment())
        }

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@BasketFragment)
                .commit()
            (activity as MainActivity).backFragmentCatalog()
        }

        return view
    }


    private fun loadFragment(fm: Fragment) {
        val ft: FragmentTransaction =
            (activity as MainActivity).supportFragmentManager.beginTransaction()
        ft.replace(R.id.content, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    override fun getBasket(basket: ArrayList<Basket>) {
        clickCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                clickRemoveAllBasket.visibility = View.VISIBLE
            } else {
                clickRemoveAllBasket.visibility = View.GONE
            }
        }
        for (item in basket) {
            if (item.getIsChecked()!!.toInt() == 1) {
                clickCheckBox.isChecked = true
            } else {
                clickCheckBox.isChecked = false
            }
        }
        val myAdapter = AdapterBasket(basket, this@BasketFragment)
        if (myAdapter != null) {
            recyclerViewBasket.adapter = myAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    fun getOrderView(basket: Int) {
        titleCountBasket.text = "$basket товар"
        Toast.makeText(requireContext(), "$basket", Toast.LENGTH_SHORT).show()
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

    override fun notBasketProducts() {
        constraintLayoutBaskerView.visibility = View.GONE
        constraintLayoutNotBasket.visibility = View.VISIBLE
    }

    fun notBasketProductsList() {
        constraintLayoutBaskerView.visibility = View.GONE
        constraintLayoutNotBasket.visibility = View.VISIBLE
    }
}