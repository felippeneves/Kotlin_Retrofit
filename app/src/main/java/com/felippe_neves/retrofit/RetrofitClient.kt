package com.felippe_neves.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton
class RetrofitClient private constructor() {

    companion object {

        private lateinit var mRetrofit: Retrofit
        private val baseUrl = "https://jsonplaceholder.typicode.com/"

        private fun getRetrofitInstance() : Retrofit {

            if(!::mRetrofit.isInitialized) {

                val httpClient = OkHttpClient.Builder()

                mRetrofit = Retrofit.Builder()
                                    .baseUrl(baseUrl)
                                    .client(httpClient.build())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
            }

            return mRetrofit
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}