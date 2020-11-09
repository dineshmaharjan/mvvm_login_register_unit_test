package io.cloudyfox.interview.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidatorUtils {

    /**
     * the input is not valid if...
     * ...the required field is empty
     */
    fun isEmptyField(value: String?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return true
    }

    /**
     * the input is not valid if...
     * .. the full name is empty
     * ..the email is empty
     * .. the password and confirm password is empty
     */
    fun validateRegister(
        fullName: String?,
        email: String?,
        password: String?,
        confirmPswd: String?
    ): Boolean {
        if (fullName.isNullOrEmpty()) {
            return false
        }

        if (email.isNullOrEmpty()) {
            return false
        }

        if (password.isNullOrEmpty()) {
            return false
        }

        if (confirmPswd.isNullOrEmpty()) {
            return false
        }

        return true
    }

    /**
     * the input email is not valid if..
     * ..email pattern is not matched
     */
    fun isEmailValid(email: String?): Boolean {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches()
    }

    /**
     * the input is not valid if...
     * ..confirm password is not matched with password
     */
    fun isPasswordConfirmPasswordMatch(password: String?, confirmPswd: String?): Boolean {
        if (!confirmPswd.equals(password)) {
            return false
        }
        return true
    }

    /**
     * the input is not valid if...
     * ..password length is less than 6
     */
    fun checkPasswordLength(password: String?): Boolean {
        if (password!!.count { it.isDigit() } < 6) {
            return false
        }
        return true
    }
}