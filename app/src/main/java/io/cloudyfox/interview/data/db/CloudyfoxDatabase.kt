package io.cloudyfox.interview.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class CloudyfoxDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}