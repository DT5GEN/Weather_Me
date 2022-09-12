package com.deeppowercrew.weatherme

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.deeppowercrew.weatherme.data.WeatherModel
import com.deeppowercrew.weatherme.screens.MainCard
import com.deeppowercrew.weatherme.screens.TableLayout
import com.deeppowercrew.weatherme.ui.theme.WeatherMeTheme
import org.json.JSONObject

const val API_KEY = "9c4dca2eee744d2f9ba134332220209"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherMeTheme {
                val dayzList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                getWeatherData("Yokohama", this, dayzList)
                Image(
                    painter = painterResource(id = R.drawable.backgrnd),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.Crop
                )
                Column {
                    MainCard()
                    TableLayout(dayzList)
                }
            }

        }
    }
}

fun getWeatherData(city: String, context: Context, daysList: MutableState<List<WeatherModel>>) {
    val url =
        "https://api.weatherapi.com/v1/forecast.json" + "?key=$API_KEY&" + "q=$city" + "&days=" +
                "7" +
                "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url, { response ->
            val list = getWeatherByDays(response)
            daysList.value = list
            Log.d("MyLog", "Volley Error $response")
        }, { error ->
            Log.d("MyLog", "Volley Error $error")

        }
    )


    queue.add(stringRequest)
}

private fun getWeatherByDays(responseWeatherDays: String): List<WeatherModel> {
    if (responseWeatherDays.isEmpty()) return listOf()
    val list = ArrayList<WeatherModel>()
    val mainObject = JSONObject(responseWeatherDays)
    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()) {
        val itemDay = days[i] as JSONObject
        list.add(
            WeatherModel(
                city,
                itemDay.getString("date"),
                "",
                itemDay.getJSONObject("day").getJSONObject("condition").getString("text"),
                itemDay.getJSONObject("day").getJSONObject("condition").getString("icon"),
                itemDay.getJSONObject("day").getString("maxtemp_c"),
                itemDay.getJSONObject("day").getString("mintemp_c"),
                itemDay.getJSONArray("hour").toString()

            )
        )
    }
    list[0] = list[0].copy(

        timeUpdate = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c")

    )
    return list
}
