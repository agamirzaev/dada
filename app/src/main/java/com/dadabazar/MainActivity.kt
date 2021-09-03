package com.dadabazar

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.fragment.*
import com.dadabazar.utills.Preferences
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: BottomNavigationView
    private lateinit var menuPanelProfile: ConstraintLayout
    private var manuItem: Int = 1

    private lateinit var TradingPanel: TextView
    private lateinit var profile: TextView
    private lateinit var logaut: TextView
    private lateinit var hepl: TextView
    private lateinit var wishlist: TextView
    private lateinit var myOrder: TextView
    private lateinit var settings: TextView
    private lateinit var b2b: TextView
    private lateinit var lnGroup: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuPanelProfile = findViewById(R.id.menuPanelProfile)
        TradingPanel = findViewById(R.id.TradingPanel)
        profile = findViewById(R.id.profile)
        logaut = findViewById(R.id.logaut)
        hepl = findViewById(R.id.hepl)
        wishlist = findViewById(R.id.wishlist)
        myOrder = findViewById(R.id.myOrder)
        settings = findViewById(R.id.settings)
        b2b = findViewById(R.id.b2b)
        lnGroup = findViewById(R.id.lnGroup)

        navigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(this)

        menuPanelProfile.setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                return@OnTouchListener true
            }
            event.action == MotionEvent.ACTION_UP
        })

        if (Preferences.loadGroupId(this).toString() != "") {
            exeptionClose()
        }

        TradingPanel.setOnClickListener {
            menuItemViewGone()
            loadFragment(ControlPanelFragment())
        }

        b2b.setOnClickListener {
            menuItemViewGone()

        }

        settings.setOnClickListener {
            menuItemViewGone()
            loadFragment(AccountSettingsFragment())
        }

        myOrder.setOnClickListener {
            menuItemViewGone()
            loadFragment(MyOrdersFragment())
        }

        wishlist.setOnClickListener {
            menuItemViewGone()
            backWishlistFragment()
        }

        hepl.setOnClickListener {
            menuItemViewGone()
            loadFragment(HelpDadabazarFragment())
        }

        profile.setOnClickListener {
            menuItemViewGone()
            loadFragment(OfficeFragment())
        }

        logaut.setOnClickListener {
            Preferences.saveUserId("", this)
            Preferences.saveGroupId("", this)
            supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .remove(ProfileUserFragment())
                    .commit()
            backOfficeFragment()
            menuItemViewGone()

            if (Preferences.loadUserId(this).toString() != "") {
                basketCount(0)
            }
        }

        loadFragment(MainFragment())
    }

    fun basketCount(count: Int) {
        if (count != 0) {
            val badge = navigationView.getOrCreateBadge(R.id.navigation_bag)
            badge.isVisible = true
            badge.number = count
            badge.badgeGravity = BadgeDrawable.TOP_END
            badge.backgroundColor = resources.getColor(R.color.purple_700)
        }
    }

    fun loadFragment(fm: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    override fun onBackPressed() {
        if (navigationView.selectedItemId ==  R.id.navigation_main) {
            super.onBackPressed()
            finish()
        } else {
            navigationView.selectedItemId =  R.id.navigation_main
        }
    }

    fun exeptionClose() {
        if (Preferences.loadGroupId(this).toString().toInt() == 0 ||
                Preferences.loadGroupId(this).toString().toInt() == 1 ||
                Preferences.loadGroupId(this).toString().toInt() == 2
        ) {
            lnGroup.visibility = View.VISIBLE
            profile.visibility = View.VISIBLE
        } else {
            lnGroup.visibility = View.GONE
            profile.visibility = View.GONE
        }

    }

    fun navigationMainFragment() {
        navigationView.selectedItemId = R.id.navigation_catalog
    }

    fun backFragmentCatalog() {
        navigationView.selectedItemId = R.id.navigation_main
    }

    fun backFragmentMain() {
        navigationView.selectedItemId = R.id.navigation_main
    }

    fun backDetailsCatalogFragment() {
        navigationView.selectedItemId = R.id.navigation_catalog
    }

    fun backOfficeFragment() {
        navigationView.selectedItemId = R.id.navigation_office
    }


    fun backWishlistFragment() {
        navigationView.selectedItemId = R.id.navigation_bookmarks
    }


    fun backBasketFragment() {
        navigationView.selectedItemId = R.id.navigation_bag
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_main -> {
                manuItem = 1
                menuPanelProfile.visibility = View.GONE
                loadFragment(MainFragment())
                return true
            }
            R.id.navigation_catalog -> {
                manuItem = 1
                menuPanelProfile.visibility = View.GONE
                loadFragment(CatalogFragment())
                return true
            }
            R.id.navigation_bag -> {
                if (Preferences.loadUserId(this) != "") {
                    manuItem = 1
                    menuPanelProfile.visibility = View.GONE
                    loadFragment(BasketFragment())
                } else {
                    alertDialog()
                    return false
                }
                return true
            }
            R.id.navigation_bookmarks -> {
                if (Preferences.loadUserId(this) != "") {
                    manuItem = 1
                    menuPanelProfile.visibility = View.GONE
                    loadFragment(WishlistFragment())
                } else {
                    alertDialog()
                    return false
                }
                return true
            }
            R.id.navigation_office -> {
                if (Preferences.loadUserId(this) != "") {
                    menuItemViewView()
                } else {
                    loadFragment(OfficeFragment())
                }
                return true
            }
        }
        return false
    }

    private fun menuItemViewView() {
        when (manuItem) {
            1 -> {
                manuItem = 0
                menuPanelProfile.visibility = View.VISIBLE
            }
            0 -> {
                manuItem = 1
                menuPanelProfile.visibility = View.GONE
            }
            else -> {
                menuPanelProfile.visibility = View.VISIBLE
            }
        }
    }

    fun menuItemViewGone() {
        manuItem = 1
        menuPanelProfile.visibility = View.GONE
    }

    fun alertDialog(): AlertDialog.Builder {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Это действие требует авторизации!")
                .setMessage("Пожалуйста, войдите в свою учетную запись, чтобы выполнить это действие, или зарегистрируйтесь, если у вас еще нет учетной записи - DadaBazar")
                .setCancelable(false)
                .setNegativeButton("Окей") { dialog, _ ->
                    dialog.cancel()
                }
        val alert: AlertDialog = builder.create()
        alert.show()
        return builder
    }
}