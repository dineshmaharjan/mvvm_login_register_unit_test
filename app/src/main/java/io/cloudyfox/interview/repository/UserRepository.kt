package io.cloudyfox.interview.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.cloudyfox.interview.data.GeneralPojo
import io.cloudyfox.interview.data.db.User
import io.cloudyfox.interview.data.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(val userDao: UserDao) {

    suspend fun registerUser(user: User): MutableLiveData<GeneralPojo> {
        var generalPojo = MutableLiveData<GeneralPojo>()
        if (userDao.insertUser(user) > 0) {
            generalPojo.value = GeneralPojo("${user.fullName} is created", "00")
            return generalPojo
        }
        generalPojo.value = GeneralPojo("${user.fullName} is failed to create", "99")
        return generalPojo
    }

    fun getUser(email: String) = userDao.getUser(email)

    fun login(email: String, password: String):LiveData<User> = userDao.login(email, password)

}