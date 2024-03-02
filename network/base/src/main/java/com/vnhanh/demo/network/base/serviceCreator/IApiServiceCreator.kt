package com.vnhanh.demo.network.base.serviceCreator

import kotlin.reflect.KClass

interface IApiServiceCreator {
    fun <T : Any> createApiService(inputClass: KClass<T>, apiUrl: String): T
}
