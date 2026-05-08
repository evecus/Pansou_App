package com.pansou.app.data

import com.pansou.app.model.ApiResponse
import com.pansou.app.model.HealthResponse
import com.pansou.app.model.SearchRequest
import com.pansou.app.model.SearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface PanSouApi {
    @POST("api/search")
    suspend fun search(@Body request: SearchRequest): Response<ApiResponse<SearchResponse>>

    @GET("api/health")
    suspend fun health(): Response<ApiResponse<HealthResponse>>
}

object ApiClient {
    private var retrofit: Retrofit? = null
    private var currentBaseUrl: String = ""

    fun getApi(baseUrl: String): PanSouApi {
        val normalized = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"
        if (retrofit == null || currentBaseUrl != normalized) {
            currentBaseUrl = normalized
            val client = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(normalized)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(PanSouApi::class.java)
    }
}
