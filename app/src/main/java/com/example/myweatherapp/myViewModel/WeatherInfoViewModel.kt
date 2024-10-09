package com.example.myweatherapp.myViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.mymodel.CubicWeather
import com.example.myweatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel(){

    public val weather : StateFlow<CubicWeather?>
        get() =  weatherRepository.weatherData

    fun getWeatherInfo(city : String){
        viewModelScope.launch {
            weatherRepository.getWeatherData(city)
        }
    }

}