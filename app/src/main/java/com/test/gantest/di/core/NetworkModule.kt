package com.test.gantest.di.core

import com.test.gantest.data.api.BadService
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(HttpUrl.parse(baseUrl)!!)
            .build()
    }

    @Singleton
    @Provides
    fun provideBadService(retrofit: Retrofit): BadService{
        return retrofit.create(BadService::class.java)
    }
}