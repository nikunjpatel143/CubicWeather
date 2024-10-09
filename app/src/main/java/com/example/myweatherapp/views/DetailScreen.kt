package com.example.myweatherapp.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myweatherapp.healper.SharedPreferencesHelper
import com.example.myweatherapp.myViewModel.WeatherInfoViewModel
import com.example.myweatherapp.mymodel.CubicWeather


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(city: String) {

    val weatherInfoViewModel: WeatherInfoViewModel = hiltViewModel()
    val weatherData: State<CubicWeather?> = weatherInfoViewModel.weather.collectAsState()

    weatherInfoViewModel.getWeatherInfo(city)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${weatherData.value?.name}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Current : ${weatherData.value?.main?.temp} \u2103 ${
                weatherData.value?.weather?.get(
                    0
                )?.main
            }",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${weatherData.value?.main?.temp_max} ℃",
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Black
        )

        Text(
            text = "${weatherData.value?.main?.temp_min} ℃",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Black
        )

    }


}

@Preview(showBackground = true)
@Composable
fun previewMainScreen() {
    DetailScreen("")
}



