package io.cloudyfox.interview.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user_table WHERE email = :email")
    fun getUser(email: String): LiveData<User>

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :pswd")
    fun login(email: String, pswd: String): LiveData<User>
}