package com.example.khabeer.di

import com.example.khabeer.data.network.ServicesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi() : ServicesApi =
        Retrofit.Builder()
            .baseUrl("http://40.127.194.127:5656/Salamtak/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServicesApi::class.java)

}