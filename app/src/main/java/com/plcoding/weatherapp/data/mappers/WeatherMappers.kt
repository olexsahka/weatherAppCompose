package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.WeatherDTO
import com.plcoding.weatherapp.data.remote.WeatherDataDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index: Int, time: String ->
        val temp = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexWeatherData(
            index = index, data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temp,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index/ 24
    }.mapValues { map ->
        map.value.map { it.data }
    }
}

fun WeatherDTO.toWeatherInfo(): WeatherInfo{
    val weatherDataMap =weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute<30) now.hour else now.hour +1
        it.time.hour == hour
    }
    return WeatherInfo(weatherMapDataPerDay = weatherDataMap, currentWeatherData = currentWeatherData)
}