package com.bilgehandemirkaya.manbox.weatherApi

import com.bilgehandemirkaya.manbox.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherapi {
    @GET("/data/2.5/weather?q=ankara&appid=190474e6d8fdd997583efab23ca3c5e7")
    fun getweather(
    ): Call<WeatherModel>
}
