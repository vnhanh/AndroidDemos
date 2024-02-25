package com.vnhanh.demo.network.base

interface IApiLauncher<ApiResponse, HandledResult> {
    suspend operator fun invoke(
        apiCall: suspend () -> ApiResponse,
        onComplete: suspend (HandledResult) -> Unit,
    )
}
