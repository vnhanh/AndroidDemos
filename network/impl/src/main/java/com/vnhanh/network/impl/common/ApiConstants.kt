package com.vnhanh.network.impl.common

object ApiConstant {
    const val DI_SCOPE_NAME_NETWORK = "network"

    const val CONNECTION_TIMEOUT = 60L
    const val OKHTTP_READ_TIMEOUT = 60L
    const val OKHTTP_WRITE_TIMEOUT = 60L

    const val ERROR_API_CODE_UPGRADING_SERVER = -2
    const val ERROR_HANDLED_CODE_EXCEPTION_OR_DEFAULT_FAILURE = -6

    val RANGE_SUCCESS_RESPONSE_CODE: IntRange = 1 until 400

    const val authenticationKeyNotSupplied = "authentication key not supplied"

    const val ERROR_HANDLED_CODE_SHOULD_LOG_OUT = -1
    const val ERROR_HANDLED_CODE_DEFAULT_API_EXCEPTION_ = 998
    const val ERROR_HANDLED_CODE_NO_NETWORK_CONNECTION = 999

    const val loginAgainErrMsg = "Login again"
    const val checkNetworkConnectionMgs = "Please check your internet connection"

    const val somethingWentWrongPlsContactStrFormat =
        "Something went wrong.\nPlease contact support for help %s"
    const val somethingWentWrongMsg = "Something went wrong. Please contact support for help"

    const val authorizationHeader = "Authorization"
    const val tokenHeader = "tokencofox"
}
