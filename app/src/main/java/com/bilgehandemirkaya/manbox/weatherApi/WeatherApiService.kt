package com.bilgehandemirkaya.manbox.weatherApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") country: String,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}
