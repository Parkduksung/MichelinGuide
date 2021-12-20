package com.example.navermap.fragment.api

import com.example.navermap.fragment.api.response.ResultPath
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApi {
    @GET("v1/driving")
    fun getPath(
        @Header("X-NCP-APIGW-API-KEY-ID") apiKeyID: String,
        @Header("X-NCP-APIGW-API-KEY") apiKey: String,
        @Query("start") start: String,
        @Query("goal") goal: String,
    ): Call<ResultPath>
}