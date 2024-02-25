package com.vnhanh.network.impl

import com.google.gson.GsonBuilder
import com.vnhanh.demo.network.base.IApiServiceCreator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass


class ApiServiceCreatorImpl constructor(private val okHttpClient: OkHttpClient) :
    IApiServiceCreator {

    private val gson = GsonBuilder()
        .setLenient()
        .setPrettyPrinting()
        .create()

    override fun <T : Any> createApiService(inputClass: KClass<T>, apiUrl: String): T =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(inputClass.java)
}
