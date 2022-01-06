package com.example.kakaomap.fragment.api

import com.example.kakaomap.fragment.api.response.DirectionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {

    companion object {
        private const val REST_API_KEY = "2589743509f4dc15a9daac858e75347b"
        private const val DIRECTION = "v1/direction"
    }

    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET(DIRECTION)
    fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("summary") summary: Boolean = true
    ): Call<DirectionResponse>

}