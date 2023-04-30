package com.clean.architecture.di

import com.clean.data.api.MovieApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideDBReference(): DatabaseReference {
        return FirebaseDatabase
            .getInstance("https://makingfriends-c2a68-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference

    }

}