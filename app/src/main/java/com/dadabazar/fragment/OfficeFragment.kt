package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.data.model.UserLogin
import com.dadabazar.mvp.login_user.LoginUserController
import com.dadabazar.mvp.login_user.LoginUserPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class OfficeFragment : Fragment(), LoginUserController.View {
    private lateinit var btnBackCatalog: ImageView
    private lateinit var btnResetPassword: TextView
    private lateinit var btnRegister: TextView

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var btnLoginUser: MaterialButton

    private lateinit var presenter: LoginUserPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_office, container, false)
        presenter =
            LoginUserPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@OfficeFragment)

        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)
        btnResetPassword = view.findViewById(R.id.btnResetPassword)
        btnRegister = view.findViewById(R.id.btnRegister)
        btnLoginUser = view.findViewById(R.id.btnLoginUser)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        editTextEmail = view.findViewById(R.id.editTextEmail)

        btnLoginUser.setOnClickListener {

            if (editTextEmail.text.toString() != "" && editTextPassword.text.toString() != "") {
                presenter.responseLoginUser(
                    "2AOmTW",
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            } else {
                Toast.makeText(context, "Введите данные для входа", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            val fragment: Fragment = RegistrationFragment()
            val fragManager: FragmentManager =
                (view.context as AppCompatActivity).supportFragmentManager
            val ft: FragmentTransaction = fragManager.beginTransaction()
            ft.replace(R.id.content, fragment)
            ft.commit()
        }

        btnResetPassword.setOnClickListener {
            val fragment: Fragment = ForgotYourPasswordFragment()
            val fragManager: FragmentManager =
                (view.context as AppCompatActivity).supportFragmentManager
            val ft: FragmentTransaction = fragManager.beginTransaction()
            ft.replace(R.id.content, fragment)
            ft.commit()
        }

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@OfficeFragment)
                .commit()
            (activity as MainActivity).backFragmentMain()
        }
        return view
    }

    override fun getLoginUser(user: UserLogin) {
        if (user.getStatus() == true) {
            Preferences.saveUserId(user.getId().toString(), requireContext())
            Preferences.saveGroupId(user.getGroupId().toString(), requireContext())
            (activity as MainActivity).loadFragment(MainFragment())
            (activity as MainActivity).backFragmentMain()
            (activity as MainActivity).exeptionClose()
            Toast.makeText(context, "Вход прошел успешно", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Нет такого пользователя", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }

    override fun onResume() {
        if (Preferences.loadUserId(requireContext()).toString() != "") {
            (activity as MainActivity).basketCount(0)
        }
        super.onResume()
    }

    private fun loadFragment(fragment: Fragment) {
        val fragManager: FragmentManager =
            (activity as AppCompatActivity).supportFragmentManager
        val ft: FragmentTransaction = fragManager.beginTransaction()
        ft.replace(R.id.content, fragment)
        ft.commit()
    }

    override fun onStart() {
        if (Preferences.loadUserId(requireContext()).toString() != "") {
            loadFragment(ProfileUserFragment())
            (activity as MainActivity).basketCount(0)
        } else {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(ProfileUserFragment())
                .commit()
        }
        super.onStart()
    }
}