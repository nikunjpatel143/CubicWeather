package com.example.myweatherapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.myweatherapp.healper.SharedPreferencesHelper
import com.example.myweatherapp.mymodel.CubicWeather
import com.example.myweatherapp.weaterhAPI.WeatherApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi,@ApplicationContext context : Context) {

    private val _weatherData = MutableStateFlow<CubicWeather?>(null)
    public val weatherData: StateFlow<CubicWeather?> get() = _weatherData.asStateFlow()
    private val sharedPreferencesHelper = SharedPreferencesHelper(context)
    private val context = context

    suspend fun getWeatherData(city : String) {
        val response = weatherApi.getWeatherInfo(city)

        if (response.isSuccessful && response.body() != null) {
            val weather = response.body()
            if (weather != null) {
                sharedPreferencesHelper.saveCity(weather.name)
            }
            _weatherData.emit(weather!!)
        }else{
            Toast.makeText(context, "Sorry, no data found. Please try again later.", Toast.LENGTH_LONG).show()
        }
    }

}