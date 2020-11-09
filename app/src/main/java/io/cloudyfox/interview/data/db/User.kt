package io.cloudyfox.interview.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(var fullName: String?, var email: String?, var password: String?):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}