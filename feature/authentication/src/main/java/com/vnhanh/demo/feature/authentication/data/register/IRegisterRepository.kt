package com.vnhanh.demo.feature.authentication.data.register

import com.vnhanh.network.impl.data.ApiResponse

interface IRegisterRepository {
    suspend fun register(email: String, password: String): ApiResponse<Nothing>
}
