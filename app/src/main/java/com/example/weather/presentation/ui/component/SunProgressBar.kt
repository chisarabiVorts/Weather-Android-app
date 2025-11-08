package com.example.weather.presentation.ui.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.presentation.utils.WeatherIcons
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt


@SuppressLint("UnrememberedMutableState")
@Composable
fun SunProgressBar(
    sunrisTime: String,
    sunsTime: String ,
    iconSize: Dp = 24.dp,
    barHeight: Dp = 4.dp
) {


    val formatter = DateTimeFormatter.ofPattern("hh:mm a")

    val sunriseTime = LocalTime.parse(sunrisTime, formatter)
    val sunsetTime = LocalTime.parse(sunsTime, formatter)

    val sunriseMillis = sunriseTime.toSecondOfDay() * 1000L
    val sunsetMillis = sunsetTime.toSecondOfDay() * 1000L
    val nowMoscow = LocalTime.now(ZoneId.of("Europe/Moscow"))
    val currentMillis = nowMoscow.toSecondOfDay() * 1000L

    val progress = ((currentMillis - sunriseMillis).toFloat() / (sunsetMillis - sunriseMillis))
        .coerceIn(0f, 1f)
    Log.d("Test","${progress * 100}%")

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = WeatherIcons.Sunset,
                    contentDescription = "Sun position",
                    tint = Color.White,
                    modifier = Modifier
                        .wrapContentSize()
                )
                Text(
                    text = stringResource(R.string.Sunrise),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = WeatherIcons.Sunrise,
                    contentDescription = "Sun position",
                    tint = Color.White,
                    modifier = Modifier
                        .wrapContentSize()
                )
                Text(
                    text = stringResource(R.string.Sunset),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )

            }

        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(iconSize + barHeight)
        ) {
            val density = LocalDensity.current
            val widthPx = with(density) { maxWidth.toPx() }
            val iconSizePx = with(density) { iconSize.toPx() }

            val iconOffsetX = (widthPx * progress - iconSizePx / 2).coerceIn(0f, widthPx - iconSizePx)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .height(barHeight)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(barHeight / 2)
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = progress)
                    .align(Alignment.CenterStart)
                    .height(barHeight)
                    .background(Color.Gray, shape = RoundedCornerShape(barHeight / 2))
            )

            Icon(
                imageVector = WeatherIcons.CurrentSun,
                contentDescription = "Sun position",
                tint = Color.White,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopStart)
                    .offset { IntOffset(iconOffsetX.roundToInt(), 0) }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$sunriseTime",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.wrapContentSize(),
                color = Color.White
            )
            Text(
                text = "$sunsetTime",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.wrapContentSize(),
                color = Color.White
            )
        }
    }
}
