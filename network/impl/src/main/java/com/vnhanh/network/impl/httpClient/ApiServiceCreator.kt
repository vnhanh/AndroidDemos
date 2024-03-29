package com.vnhanh.network.impl.httpClient

import com.google.gson.GsonBuilder
import com.vnhanh.demo.network.base.serviceCreator.IApiServiceCreator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass


class ApiServiceCreatorImpl(private val okHttpClient: OkHttpClient) :
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
