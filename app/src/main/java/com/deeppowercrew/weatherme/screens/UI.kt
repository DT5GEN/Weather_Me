package com.deeppowercrew.weatherme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.deeppowercrew.weatherme.ui.theme.BlueUltraLight

@Preview(showBackground = true)
@Composable
fun ListItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        backgroundColor = BlueUltraLight,
        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp)) {
                Text(text = "12:00")
                Text(
                    text = "sunny",
                    color = Color.White
                )
            }

            Text(
                text = "33 C",
                color = Color.White,
                fontSize = 24.sp

            )


            AsyncImage(
                model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                contentDescription = "weather_icon",
                modifier = Modifier
                    .padding(top = 4.dp, end = 8.dp)
                    .size(40.dp)
            )
        }

    }
}