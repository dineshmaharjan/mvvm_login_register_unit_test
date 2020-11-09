package io.cloudyfox.interview.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import io.cloudyfox.interview.R
import io.cloudyfox.interview.ui.viewmodels.UserViewModel
import io.cloudyfox.interview.utils.ValidatorUtils
import kotlinx.android.synthetic.main.activity_register.*


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(R.layout.activity_register) {
    private val viewModel: UserViewModel by viewModels()
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
        auth = FirebaseAuth.getInstance()
        signinLayout.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }

        signUpBttn.setOnClickListener {
            var fullName = fullNameEdtText.text.toString().trim()
            var email = emailEditText.text.toString().trim()
            var password = passwordEditText.text.toString().trim()
            var confirmPswd = confirmPasswordEditText.text.toString().trim()
            register(fullName, email, password, confirmPswd)
        }

    }

    private fun initComponents() {
        fullNameEdtText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (fullNameTextInputLayout.isErrorEnabled) {
                    fullNameTextInputLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (emailTextInputLayout.isErrorEnabled) {
                    emailTextInputLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (passwordTextInputLayout.isErrorEnabled) {
                    passwordTextInputLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (confirmPasswordTextInputLayout.isErrorEnabled) {
                    confirmPasswordTextInputLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


    }

    private fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (ValidatorUtils.validateRegister(fullName, email, password, confirmPassword)) {

            if (ValidatorUtils.isEmailValid(email)) {
                if(ValidatorUtils.checkPasswordLength(password)) {
                    if (ValidatorUtils.isPasswordConfirmPasswordMatch(password, confirmPassword)) {
                        progressBar.visibility = View.VISIBLE
                        signUpBttn.visibility = View.GONE
                        viewModel.register(fullName, email, password)
                        viewModel.generalPojo.observe(this, Observer {
                            when (it.status) {
                                "00" -> viewModel.navigateToLogin(this)
                                "99" -> {
                                    progressBar.visibility = View.GONE
                                    signUpBttn.visibility = View.VISIBLE
                                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                }
                            }

                        })


                    } else {
                        confirmPasswordTextInputLayout.error =
                            getString(R.string.error_confirm_pswd)
                        confirmPasswordEditText.requestFocus()
                    }
                }else{
                    passwordTextInputLayout.error=getString(R.string.error_password_legth)
                    passwordEditText.requestFocus()
                }
            } else {
                emailTextInputLayout.error = getString(R.string.error_email)
                emailEditText.requestFocus()
            }

        } else {
            Snackbar.make(mainLayout, getString(R.string.error_all_fields), Snackbar.LENGTH_LONG)
                .setTextColor(
                    Color.WHITE
                ).show()
        }
    }
}