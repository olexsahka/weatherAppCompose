package com.plcoding.weatherapp.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.presentation.WeatherState

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {
    state.weatherInfo?.weatherMapDataPerDay?.get(0)?.let { weatherDataList ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Tooday", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
                items(weatherDataList) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(120.dp)
                            .padding(16.dp)
                    )
                }
            })
        }
    }

}