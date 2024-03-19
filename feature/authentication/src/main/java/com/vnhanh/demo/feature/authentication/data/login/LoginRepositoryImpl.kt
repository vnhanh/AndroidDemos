package com.vnhanh.demo.feature.authentication.data.login

import UserResponse
import com.vnhanh.demo.feature.authentication.data.api.AuthenticationApi
import com.vnhanh.network.impl.data.ApiResponse

class LoginRepositoryImpl(
    private val api: AuthenticationApi,
) : ILoginRepository {
    override suspend fun login(email: String, password: String): ApiResponse<UserResponse> {
        // TODO: implement it
        return ApiResponse()
    }

}
