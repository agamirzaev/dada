package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.data.model.UserRegister
import com.dadabazar.mvp.login_user.LoginUserPresenter
import com.dadabazar.mvp.register_user.RegisterUserController
import com.dadabazar.mvp.register_user.RegisterUserPresenter
import com.dadabazar.utills.Preferences
import com.google.android.material.textfield.TextInputEditText

class RegistrationFragment : Fragment(), RegisterUserController.View {

    private lateinit var presenter: RegisterUserPresenter

    private lateinit var btnBackCatalog: ImageView
    private lateinit var btnLoginHere: TextView
    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var configPassword: TextInputEditText
    private lateinit var checkBox: AppCompatCheckBox
    private lateinit var btnRegistration: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)
        btnLoginHere = view.findViewById(R.id.btnLoginHere)
        firstName = view.findViewById(R.id.firstName)
        lastName = view.findViewById(R.id.lastName)
        username = view.findViewById(R.id.username)
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        configPassword = view.findViewById(R.id.configPassword)
        checkBox = view.findViewById(R.id.checkBox)
        btnRegistration = view.findViewById(R.id.btnRegistration)

        presenter =
            RegisterUserPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@RegistrationFragment)

        btnLoginHere.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@RegistrationFragment)
                .commit()
            (activity as MainActivity).backOfficeFragment()
        }
        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@RegistrationFragment)
                .commit()
            (activity as MainActivity).backOfficeFragment()
        }

        btnRegistration.setOnClickListener {
            presenter.responseRegisterUser(
                "2AOmTW",
                firstName.text.toString(),
                lastName.text.toString(),
                username.text.toString(),
                email.text.toString(),
                password.text.toString(),
                configPassword.text.toString()
            )
        }

        return view
    }

    override fun getRegisterUser(user: UserRegister) {
        if (user.getStatus() == true) {
            Preferences.saveGroupId(user.getGroupId().toString(), requireContext())
            Preferences.saveUserId(user.getId().toString(), requireContext())
            (activity as MainActivity).loadFragment(MainFragment())
            (activity as MainActivity).backFragmentMain()
            (activity as MainActivity).exeptionClose()
            Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
        } else {
            when {
                user.getMessage().toString() == "invalid_fname" -> {
                    Toast.makeText(context, "Введите ваше имя", Toast.LENGTH_SHORT).show()
                }
                user.getMessage().toString() == "invalid_lname" -> {
                    Toast.makeText(context, "Введите вашу фамилию", Toast.LENGTH_SHORT).show()
                }
                user.getMessage().toString() == "invalid_uname" -> {
                    Toast.makeText(context, "Введите имя пользователя", Toast.LENGTH_SHORT).show()
                }
                user.getMessage().toString() == "invalid_email" -> {
                    Toast.makeText(context, "Введите адрес электронной почты", Toast.LENGTH_SHORT)
                        .show()
                }
                user.getMessage().toString() == "invalid_password" -> {
                    Toast.makeText(context, "Введите пароль", Toast.LENGTH_SHORT).show()
                }
                user.getMessage().toString() == "password_mismatch" -> {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onStart() {
        if (Preferences.loadUserId(requireContext()).toString() != "") {
            (activity as MainActivity).basketCount(0)
        }
        super.onStart()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}