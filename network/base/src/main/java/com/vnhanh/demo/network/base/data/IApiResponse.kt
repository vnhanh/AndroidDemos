package com.vnhanh.demo.network.base.data


/**
 * used for handling response
 */
interface IApiResponse {
    val code: Int?

    val message : String?

    val isErrorUpgradingServer: Boolean

    val isErrorLogOut: Boolean

    val isSuccess: Boolean
}

interface IFailedHandledResponse {
    val mustLogOut: Boolean
}
