package com.vnhanh.network.impl.data

import com.google.gson.annotations.SerializedName
import com.vnhanh.demo.network.base.IApiResponse
import com.vnhanh.network.impl.ApiConstant

data class ApiResponse<T>(
    @SerializedName("code")
    override val code: Int = 0,

    @SerializedName("message")
    override val message: String = "",

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("errors")
    val errors: FieldErrors? = null
) : IApiResponse {
    override val isErrorUpgradingServer: Boolean
        get() = code == ApiConstant.ERROR_API_CODE_UPGRADING_SERVER

    override val isErrorLogOut: Boolean
        get() = code == ApiConstant.ERROR_HANDLED_CODE_SHOULD_LOG_OUT

    override val isSuccess: Boolean
        get() = code in ApiConstant.RANGE_SUCCESS_RESPONSE_CODE
}

class FieldErrors : ArrayList<FieldErrorItem>()

data class FieldErrorItem(
    @SerializedName("field")
    val fieldName: String,
    @SerializedName("messages")
    val messages: List<String>
)
