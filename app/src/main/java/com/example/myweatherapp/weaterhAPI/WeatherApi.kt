package com.example.myweatherapp.weaterhAPI

import com.example.myweatherapp.mymodel.CubicWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather?&units=metric&APPID=b6fd93c793aaf6a2f6c6132dab483b52")
    suspend fun getWeatherInfo(@Query("q") title: String) : Response<CubicWeather>

}