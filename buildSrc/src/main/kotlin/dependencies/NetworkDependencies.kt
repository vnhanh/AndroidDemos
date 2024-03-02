package dependencies

object RetrofitDependencies {
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"

    private const val gsonConverterVersion = "2.9.0"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$gsonConverterVersion"

    private const val loggingInterceptorVersion = "4.10.0"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"
}
