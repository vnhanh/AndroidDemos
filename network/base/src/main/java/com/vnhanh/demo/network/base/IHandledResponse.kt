package com.vnhanh.demo.network.base

/**
 * Handled result response data by [IApiLauncher]
 */
interface IHandledResponse<SuccessResponse, FailureResponse: IFailedHandledResponse> {
    val successHandledResponse: SuccessResponse?

    val failedHandedResponse: FailureResponse?

    operator fun component1(): SuccessResponse? = successHandledResponse
    operator fun component2(): FailureResponse? = failedHandedResponse
}
