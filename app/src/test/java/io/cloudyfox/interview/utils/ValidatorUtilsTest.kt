package io.cloudyfox.interview.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorUtilsTest{

    @Test
    fun `empty field return false`(){
        val result=ValidatorUtils.isEmptyField(
          ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty full name return false`(){
        val result=ValidatorUtils.validateRegister(
            "",
            "www.maharjandinesh@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty email return false`(){
        val result=ValidatorUtils.validateRegister(
            "Dinesh Maharjan",
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password return false`(){
        val result=ValidatorUtils.validateRegister(
            "Dinesh Maharjan",
            "www.maharjandinesh@gmail.com",
            "",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty confirm password return false`(){
        val result=ValidatorUtils.validateRegister(
            "Dinesh Maharjan",
            "www.maharjandinesh@gmail.com",
            "123",
            ""
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `valid input return true`(){
        val result=ValidatorUtils.validateRegister(
            "Dinesh Maharjan",
            "www.maharjandinesh@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `all empty input return false`(){
        val result=ValidatorUtils.validateRegister(
            "",
            "",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email return false`(){
        val result=ValidatorUtils.isEmailValid(
            "maharjandinesh"
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `valid email return true`(){
        val result=ValidatorUtils.isEmailValid(
            "www.maharjandinesh@gmail.com"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `confirm password match with password return true`(){
        val result=ValidatorUtils.isPasswordConfirmPasswordMatch(
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `confirm password does not with password return false`(){
        val result=ValidatorUtils.isPasswordConfirmPasswordMatch(
            "1234",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `minium password length 6 return true`(){
        val result=ValidatorUtils.checkPasswordLength(
            "123456",
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `minium password length less than 6 return false`(){
        val result=ValidatorUtils.checkPasswordLength(
            "1234",
        )
        assertThat(result).isFalse()
    }



}