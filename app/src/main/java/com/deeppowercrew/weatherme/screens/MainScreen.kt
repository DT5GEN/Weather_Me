package com.deeppowercrew.weatherme.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.deeppowercrew.weatherme.R
import com.deeppowercrew.weatherme.data.WeatherModel
import com.deeppowercrew.weatherme.ui.theme.BlueLight
import com.deeppowercrew.weatherme.ui.theme.BlueUltraLight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainCard() {

    Column(
        modifier = Modifier
            .padding(5.dp),

        ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "08 aug 2022",
                        style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 18.sp),
                        color = Color.White

                    )

                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "weather_icon",
                        modifier = Modifier
                            .padding(top = 4.dp, end = 8.dp)
                            .size(40.dp)
                    )
                }

                Text(
                    text = "Tokyo",
                    style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 28.sp),
                    color = Color.White

                )
                Text(
                    text = "24 C",
                    style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 64.sp),
                    color = Color.White

                )
                Text(
                    text = "sunny",
                    style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 16.sp),
                    color = Color.White

                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    IconButton(modifier = Modifier.padding(start = 8.dp),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "image_btn",
                            tint = Color.White
                        )
                    }


                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "8  C / - 4  C",
                        style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 18.sp),
                        color = Color.White

                    )

                    IconButton(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_update_weather_info),
                            contentDescription = "image_update",
                            tint = Color.White
                        )
                    }


                }
            }

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TableLayout() {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {

        TabRow(
            selectedTabIndex = tabIndex,
            indicator = {
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, it)
                )
            },
            backgroundColor = BlueUltraLight,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { indexElement, text ->
                Tab(selected = false, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(indexElement)
                    }
                },
                    text = {
                        Text(
                            text = text,
                            style = TextStyle(
                                Color.White,
                                fontSize = 24.sp,
                                fontStyle = FontStyle.Normal
                            )
                        )
                    }


                )
            }
        }

        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)

        ) { indexPage ->
            LazyColumn(Modifier.fillMaxSize()) {
             itemsIndexed(
                 listOf(
                     WeatherModel(
                         "Tokyo",
                         "10:00",
                         "22 C",
                         "rainy",
                         "//cdn.weatherapi.com/weather/64x64/day/116.png",
                         "",
                         "",
                         ""
                     ),
                     WeatherModel(
                         "Tokyo",
                         "09/09/2022",
                         "",
                         "sunny",
                         "//cdn.weatherapi.com/weather/64x64/day/116.png",
                         "30",
                         "12",
                         "blablabla"
                     )

                 )
             ){
                 _, item ->
                 ListItem(item)
             }
            }
        }

    }
}