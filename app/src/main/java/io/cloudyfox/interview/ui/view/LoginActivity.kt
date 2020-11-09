package io.cloudyfox.interview.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.cloudyfox.interview.R
import io.cloudyfox.interview.ui.viewmodels.UserViewModel
import io.cloudyfox.interview.utils.ValidatorUtils
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    private val viewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
        signInBttn.setOnClickListener {
            var username = emailEdtText.text.toString().trim()
            var password = passwordEditText.text.toString().trim()
            userLogin(username, password)
        }

        signupLayout.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


    }

    private fun initComponents() {
        emailEdtText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

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
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (passwordTextInputLayout.isErrorEnabled) {
                    passwordTextInputLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun userLogin(email: String, password: String) {
        if (ValidatorUtils.isEmptyField(email)) {
            if (ValidatorUtils.isEmailValid(email)) {
                if (ValidatorUtils.isEmptyField(password)) {
                    progressBar.visibility = View.VISIBLE
                    signInBttn.visibility = View.GONE
                    viewModel.login(email, password).observe(this, {
                        if (it != null) {
                            viewModel.navigateToDashboard(this, it)
                        } else {
                            progressBar.visibility = View.GONE
                            signInBttn.visibility = View.VISIBLE
                            Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_LONG)
                                .show()
                        }

                    })
                } else {
                    passwordTextInputLayout.error = getString(R.string.error_password)
                    passwordEditText.requestFocus();
                }
            } else {
                emailTextInputLayout.error = getString(R.string.error_email)
                emailEdtText.requestFocus();
            }
        } else {
            emailTextInputLayout.error = getString(R.string.error_all_fields)
            emailEdtText.requestFocus();
        }
    }


}