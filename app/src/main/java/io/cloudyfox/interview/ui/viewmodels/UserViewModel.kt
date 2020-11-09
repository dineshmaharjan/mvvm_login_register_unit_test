package io.cloudyfox.interview.ui.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.cloudyfox.interview.data.GeneralPojo
import io.cloudyfox.interview.data.db.User
import io.cloudyfox.interview.repository.UserRepository
import io.cloudyfox.interview.ui.view.LoginActivity
import io.cloudyfox.interview.ui.view.MainActivity
import io.cloudyfox.interview.utils.ValidatorUtils
import kotlinx.coroutines.launch

class UserViewModel @ViewModelInject constructor(val userRepository: UserRepository) :
    ViewModel() {
    var generalPojo = MutableLiveData<GeneralPojo>()

    fun register(fullName: String, email: String, password: String): MutableLiveData<GeneralPojo> {

        viewModelScope.launch {
            var result = userRepository.registerUser(User(fullName, email, password))
            generalPojo.postValue(result.value)
        }
        return generalPojo
    }

    fun login(email: String, password: String): LiveData<User> {
        return userRepository.login(email, password)
    }


    fun navigateToDashboard(activity: Activity, user: User) {
        var intent = Intent(activity, MainActivity::class.java)
        intent!!.putExtra("user", user)
        activity.startActivity(intent)
        activity.finish()
    }

    fun navigateToLogin(activity: Activity) {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }


}