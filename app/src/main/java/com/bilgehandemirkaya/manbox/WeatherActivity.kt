package com.bilgehandemirkaya.manbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilgehandemirkaya.manbox.databinding.ActivityWeatherBinding
import com.bilgehandemirkaya.manbox.weatherApi.WeatherApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherApiService::class.java)

        val apiKey = "190474e6d8fdd997583efab23ca3c5e7"

        CoroutineScope(Dispatchers.IO).launch {
            val response = weatherService.getWeather("London", apiKey)
            println(response.message())

            if (response.isSuccessful) {
                val weatherData = response.body()
                // Process weatherData on the main thread
                CoroutineScope(Dispatchers.Main).launch {
                    binding.weatherText.text = weatherData?.weather?.toString()
                }
            } else {
                // Handle error
            }
        }
    }
}
