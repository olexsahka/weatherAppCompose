package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(
    val weatherMapDataPerDay: Map<Int,List<WeatherData>>,
    val currentWeatherData : WeatherData?
)