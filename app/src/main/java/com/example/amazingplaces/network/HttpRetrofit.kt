package com.example.amazingplaces.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpRetrofit {
    private var INSTANCE: Retrofit? = null
    private const val BASE_URL = "https://stub.bbeight.synetech.cz/demo/v1/"

    /**
     * It creates a Retrofit instance.
     *
     * @return A Retrofit object
     */
    private fun getRetrofit(): Retrofit {
        return INSTANCE ?:
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()

    }

    /**
     * It creates an OkHttpClient object with logging enabled.
     *
     * @return An OkHttpClient object.
     */
    private fun getHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(logging)
        return okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cache(null)
            .build()
    }

    /**
     * It returns an instance of HttpApi.
     *
     * @return A Retrofit object.
     */
    fun getHttpApi(): HttpApi{
        return getRetrofit().create(HttpApi::class.java)
    }
}