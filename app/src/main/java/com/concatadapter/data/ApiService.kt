package com.concatadapter.data

import com.concatadapter.model.FieldList
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getDynamicFormData(
        @Url url: String
    ): Response<FieldList>

    companion object {
        private lateinit var apiService: ApiService

        fun getApiService(): ApiService {
            if (::apiService.isInitialized){
                return apiService
            }
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .client(provideOkHttpClient())
                .baseUrl("http://www.airtel.com")
                .build()

            apiService = retrofit.create(ApiService::class.java)
            return apiService
        }

        private fun provideGson(): Gson {
            val gsonBuilder = GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .setLenient()
            return gsonBuilder.create()
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient().newBuilder()
                .addInterceptor(interceptor).build()
        }
    }
}
