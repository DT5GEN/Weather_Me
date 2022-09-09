package com.deeppowercrew.weatherme

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.deeppowercrew.weatherme.screens.MainCard
import com.deeppowercrew.weatherme.screens.TableLayout
import com.deeppowercrew.weatherme.ui.theme.WeatherMeTheme
const val API_KEY = "9c4dca2eee744d2f9ba134332220209"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherMeTheme {
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
                    TableLayout()
                }
            }

        }
    }
}

private fun getWeatherData(city:String, context: Context){

}
