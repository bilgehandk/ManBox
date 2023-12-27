package com.bilgehandemirkaya.manbox.weatherApi

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    // Add other necessary fields
)

data class Main(
    val temp: Double,
    // Add other necessary fields
)

data class Weather(
    val main: String,
    // Add other necessary fields
)
