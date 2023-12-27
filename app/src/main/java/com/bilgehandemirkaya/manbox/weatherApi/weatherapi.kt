package com.bilgehandemirkaya.manbox.weatherApi

import com.bilgehandemirkaya.manbox.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherapi {
    @GET("weather")
    fun getweather(
        @Query("q") cityname: String?,
        @Query("appid") apikey: String?
    ): Call<WeatherModel>
}
