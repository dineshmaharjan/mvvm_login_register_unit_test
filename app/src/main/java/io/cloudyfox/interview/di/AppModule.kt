package io.cloudyfox.interview.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.cloudyfox.interview.constant.Constants.CLOUDYFOX_DATABASE
import io.cloudyfox.interview.data.db.CloudyfoxDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCloudyfoxDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(app, CloudyfoxDatabase::class.java, CLOUDYFOX_DATABASE).build()

    @Singleton
    @Provides
    fun provideUserDao(db: CloudyfoxDatabase) = db.getUserDao()
}