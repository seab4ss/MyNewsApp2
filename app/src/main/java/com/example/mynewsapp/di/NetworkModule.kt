package com.example.mysandbox2.di

import com.example.mysandbox2.model.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Yury on 8/24/2020
 */
// @Module informs Dagger tht this class is a Dagger Module
// Install this module in Hilt-generated ApplicationComponent
@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    // Postman mock server URL
    //private val API_URL = "https://672405aa-70c9-4284-a3b7-d91efef58e3b.mock.pstmn.io"
    //private val NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=1437be957ba74f9e93cf1688a28a05ac"

    private val NEWS_API_URL = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideSandbox2Api(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(NEWS_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }
}