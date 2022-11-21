package com.example.kreditplusandroidassessment.util

import com.example.kreditplusandroidassessment.BuildConfig
import com.example.kreditplusandroidassessment.util.MovieKeyConstant.API_KEY_PARAM
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

object ServiceGenerate {
    fun<RequestType> createService(
        serviceClass: Class<RequestType>
    ): RequestType {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(logging)
        client.addInterceptor(Interceptor { chain ->
            val url = chain.request().url.newBuilder().addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY).build()
            val request = chain.request()
                .newBuilder()
                .addHeader("x-localization", "id")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .addHeader("accept", "application/vnd.github+json")
                .url(url)
                .build()
            chain.proceed(request)
        })

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}