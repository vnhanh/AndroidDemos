package com.vnhanh.demo.network.base

import com.google.gson.JsonElement

interface IApiResponse {
    val success: Int?

    val failedCode: Int?

    val message : String?

    val data: JsonElement?

    val isErrorUpgradingServer: Boolean

    val isErrorLogOut: Boolean

    val isSuccess: Boolean
}

interface IFailedHandledResponse {
    val mustLogOut: Boolean
}
