package com.vnhanh.network.impl.apiLauncher

import androidx.annotation.StringRes
import com.vnhanh.demo.network.base.IApiLauncher
import com.vnhanh.demo.network.base.IApiResponse
import com.vnhanh.demo.network.base.IFailedHandledResponse
import com.vnhanh.demo.network.base.IHandledResponse
import com.vnhanh.network.impl.common.ApiConstant.loginAgainErrMsg

/**
 * Handled result data after [IApiLauncher] implementation handle api response that implements [IApiResponse] interface
 */
open class ApiHandledResponse<T : IApiResponse>(
    override val successHandledResponse: T? = null,
    override val failedHandedResponse: FailedHandedResponse<T>? = null,
) : IHandledResponse<T, FailedHandedResponse<T>>

data class FailedHandedResponse<T : IApiResponse>(
    val code: Int?,
    val message: String?,
    @StringRes val msgId: Int? = null,
    val response: T? = null
) : IFailedHandledResponse {
    override val mustLogOut: Boolean
        get() = message != null && message == loginAgainErrMsg
}
