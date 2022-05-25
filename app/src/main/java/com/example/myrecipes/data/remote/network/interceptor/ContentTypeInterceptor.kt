package com.example.myrecipes.data.remote.network.interceptor

import com.example.myrecipes.data.remote.*
import okhttp3.Interceptor
import okhttp3.Response

class ContentTypeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(ACCEPT, CONTENT_TYPE)
            .addHeader(X_RapidAPI_Host_Key, X_RapidAPI_Host_Key_VALUE)
            .addHeader(X_RapidAPI_Key_KEY, X_RapidAPI_Key_VALUE)
            .build()
        return chain.proceed(request)
    }
}