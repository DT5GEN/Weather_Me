package com.deeppowercrew.weatherme.data

data class WeatherModel(
    val city: String,
    val timeUpdate: String,
    val currentTemp: String,
    val conditionText: String,
    val conditionIconUrl: String,
    val maxTemp: String,
    val minTemp: String,
    val hours: String,
)
