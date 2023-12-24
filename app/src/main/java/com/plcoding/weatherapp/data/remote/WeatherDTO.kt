package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDTO (
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto,
)