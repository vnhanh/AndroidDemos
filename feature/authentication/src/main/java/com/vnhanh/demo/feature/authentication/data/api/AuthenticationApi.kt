package com.vnhanh.demo.feature.authentication.data.api

import UserResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationApi {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        email: String,
        password: String,
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(
        email: String,
        password: String,
    ): Response<Unit>
}
