package com.vnhanh.network.impl

import com.vnhanh.common.log.printDebugStackTrace
import com.vnhanh.demo.network.base.IApiLauncher
import com.vnhanh.demo.network.base.IApiResponse
import com.vnhanh.network.impl.ApiConstant.ERROR_API_CODE_UPGRADING_SERVER
import com.vnhanh.network.impl.ApiConstant.ERROR_HANDLED_CODE_DEFAULT_API_EXCEPTION_
import com.vnhanh.network.impl.ApiConstant.ERROR_HANDLED_CODE_NO_NETWORK_CONNECTION
import com.vnhanh.network.impl.ApiConstant.ERROR_HANDLED_CODE_SHOULD_LOG_OUT
import com.vnhanh.network.impl.ApiConstant.checkNetworkConnectionMgs
import com.vnhanh.network.impl.ApiConstant.loginAgainErrMsg
import com.vnhanh.network.impl.ApiConstant.somethingWentWrongMsg
import com.vnhanh.network.impl.ApiConstant.somethingWentWrongPlsContactStrFormat
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ApiLauncherImpl<ApiResponse : IApiResponse> :
    IApiLauncher<Response<ApiResponse>, ApiHandledResponse<ApiResponse>> {
    private val upgradingServerErrMsg = "We are upgrading our server and will be online shortly"

    open fun handleAfterOnSuccess(successResponse: Response<ApiResponse>) {}

    override suspend fun invoke(
        apiCall: suspend () -> Response<ApiResponse>,
        onComplete: suspend (ApiHandledResponse<ApiResponse>) -> Unit,
    ) {
        try {
            val response: Response<ApiResponse> = apiCall()
            val responseBody: ApiResponse? = response.body()
            when {
                // upgrading server case
                responseBody != null && responseBody.isErrorUpgradingServer -> {
                    var message: String? = responseBody.message
                    if (message.isNullOrBlank()) {
                        message = upgradingServerErrMsg
                    }
                    ApiHandledResponse(
                        failedHandedResponse = FailedHandedResponse(
                            code = ERROR_API_CODE_UPGRADING_SERVER,
                            message = message,
                        ),
                    )
                }

                // success cases
                responseBody != null && responseBody.isSuccess -> {
                    onComplete(
                        ApiHandledResponse<ApiResponse>(successHandledResponse = responseBody)
                    )
                    handleAfterOnSuccess(response)
                }

                // other failed cases
                else -> {
                    if (responseBody?.isErrorLogOut == true) {
                        // should logout
                        onComplete(
                            ApiHandledResponse(
                                failedHandedResponse = FailedHandedResponse(
                                    code = ERROR_HANDLED_CODE_SHOULD_LOG_OUT,
                                    message = loginAgainErrMsg,
                                    response = responseBody
                                )
                            )
                        )
                    } else {
                        // other failed cases
                        onComplete(
                            defaultHandledResponse(
                                defaultCode = responseBody?.failedCode,
                                defaultErrMsg = responseBody?.message,
                                responseBody = responseBody
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printDebugStackTrace()

            val exceptionHandledResponse: ApiHandledResponse<ApiResponse> = when (e) {
                is SocketTimeoutException,
                is UnknownHostException,
                is ConnectionShutdownException,
                -> {
                    defaultHandledResponse(
                        defaultCode = ERROR_HANDLED_CODE_NO_NETWORK_CONNECTION,
                        defaultErrMsg = checkNetworkConnectionMgs,
                    )
                }

                else -> {
                    defaultHandledResponse(
                        defaultCode = ERROR_HANDLED_CODE_DEFAULT_API_EXCEPTION_,
                        defaultErrMsg = e.message ?: somethingWentWrongMsg,
                    )
                }
            }

            onComplete(exceptionHandledResponse)
        }
    }

    companion object {

        fun <T : IApiResponse> defaultHandledResponse(
            defaultCode: Int? = null,
            defaultErrMsg: String? = null,
            responseBody: T? = null,
        ): ApiHandledResponse<T> {
            return ApiHandledResponse(
                failedHandedResponse = defaultFailedHandledResponse(
                    defaultCode = defaultCode,
                    defaultErrMsg = defaultErrMsg,
                    responseBody = responseBody
                )
            )
        }

        fun <T : IApiResponse> defaultFailedHandledResponse(
            defaultCode: Int? = null,
            defaultErrMsg: String? = null,
            responseBody: T? = null,
        ): FailedHandedResponse<T> {
            val reason: String = if (!defaultErrMsg.isNullOrBlank()) {
                String.format(somethingWentWrongPlsContactStrFormat, "\n\n$defaultErrMsg")
            } else {
                somethingWentWrongMsg
            }

            return FailedHandedResponse(
                code = defaultCode ?: ApiConstant.ERROR_HANDLED_CODE_EXCEPTION_OR_DEFAULT_FAILURE,
                message = reason,
                response = responseBody
            )
        }
    }
}
