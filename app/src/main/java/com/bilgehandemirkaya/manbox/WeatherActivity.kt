package com.bilgehandemirkaya.manbox

import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bilgehandemirkaya.manbox.model.WeatherModel
import com.bilgehandemirkaya.manbox.weatherApi.weatherapi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    var et: EditText? = null
    var tv: TextView? = null
    var url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}"
    var apikey = "190474e6d8fdd997583efab23ca3c5e7"
    var manager: LocationManager? = null
    var locationListener: LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        et = findViewById<EditText>(R.id.cityTv)
        tv = findViewById<TextView>(R.id.tempTv)
    }

    fun getweather(v: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val myapi = retrofit.create(weatherapi::class.java)
        val examplecall = myapi.getweather(et!!.text.toString().trim(' '), apikey)
        examplecall!!.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.code() == 404) {
                    Toast.makeText(
                        this@WeatherActivity,
                        "Please Enter a valid City",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (!response.isSuccessful) {
                    Toast.makeText(
                        this@WeatherActivity,
                        response.code().toString() + " ",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                val mydata = response.body()
                val main = mydata!!.main
                val temp = main!!.temp
                val temperature = (temp!! - 273.15).toInt()
                Handler(Looper.getMainLooper()).post {
                    tv!!.text = String.format("Temperature: %s°C", temperature)
                }
            }

            override fun onFailure(call: Call<WeatherModel?>, t: Throwable) {
                Toast.makeText(this@WeatherActivity, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }
}
