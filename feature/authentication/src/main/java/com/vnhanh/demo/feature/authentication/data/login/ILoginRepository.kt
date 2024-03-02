package com.vnhanh.demo.feature.authentication.data.login

import UserResponse
import com.vnhanh.network.impl.data.ApiResponse

interface ILoginRepository {
    suspend fun login(email: String, password: String): ApiResponse<UserResponse>
}
