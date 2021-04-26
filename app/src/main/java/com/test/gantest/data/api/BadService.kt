package com.test.gantest.data.api

import com.test.gantest.data.repository.CharacterEntity
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * API communication setup
 */
interface BadService {

    @GET("/api/characters")
    suspend fun getCharacters(): Response<List<CharacterDto>>

}