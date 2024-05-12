package com.wmktefaternak.inkubatorapp.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.wmktefaternak.inkubatorapp.R
import com.wmktefaternak.inkubatorapp.authgoogle.UserData
import com.wmktefaternak.inkubatorapp.viewModel.MonitorViewModel


@Composable
fun MonitorsScreen(
    viewModel: MonitorViewModel = viewModel(),
    userData: UserData?,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //val viewModel = viewModel<MonitorViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val valueIndicatorTemperature = state.temperature
        val valueIndicatorHumidity  = state.humidity

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            modifier = Modifier
                .width(350.dp)
                .height(94.dp)
                .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 14.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(start = 12.dp, top = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_monitor),
                        style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            //fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(400),

                            ))
                    Spacer(modifier = Modifier.height(5.dp))
                    if (userData?.username != null) {
                        Text(text = userData.username,
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                //fontFamily = FontFamily(Font(R.font.roboto)),
                                fontWeight = FontWeight(500),

                                letterSpacing = 0.15.sp,
                            )
                        )
                    } else {
                        Text(text = "Pengguna",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                //fontFamily = FontFamily(Font(R.font.roboto)),
                                fontWeight = FontWeight(500),

                                letterSpacing = 0.15.sp,
                            )
                        )
                    }
                }
                IconButton(onClick = onSignOut, modifier = Modifier
                    .width(90.dp)
                    .padding(top = 20.dp, start = 30.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = null)
                        Text(text = stringResource(R.string.out_account))
                    }
                }
                if (userData?.profilePictureUrl != null) {
                    Box(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                        AsyncImage(
                            model = userData.profilePictureUrl,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                } else {
                    Box(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp)
                .height(46.dp)
                .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 14.dp))) {
            Text(
                text = stringResource(id = R.string.incubator_condision),
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(400),
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 9.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp),
            modifier = Modifier
                .width(350.dp)
                .height(499.dp)
                .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 14.dp))) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.monitoring),
                    style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.padding(top = 23.dp)
                )
                Box {
                    CircularIndicator(
                        sizeCircular = 2f,
                        indicatorValue = valueIndicatorTemperature,
                        maxIndicatorValue = 50,
                        foregroundIndicatorColor = MaterialTheme.colorScheme.outlineVariant
                    )
                    CircularIndicator(
                        sizeCircular = 1.25f,
                        indicatorValue = valueIndicatorHumidity,
                        maxIndicatorValue = 100,
                        foregroundIndicatorColor = MaterialTheme.colorScheme.scrim
                    )
                    Column(modifier = Modifier
                        .padding(top = 115.dp, start = 108.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                    }
                }
                Row {
                    ElevatedCard(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.scrim),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp),
                        modifier = Modifier
                            .width(138.dp)
                            .height(118.dp)
                            .background(
                                color = Color(0xFFAAF3C7), shape = RoundedCornerShape(size = 14.dp)
                            )) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.humidity_icon),
                                    contentDescription = "humidity"
                                )
                                Text(
                                    text = stringResource(id = R.string.humidity),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 24.sp,
                                        //fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(500),

                                        letterSpacing = 0.15.sp,
                                    ),
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                            }
                            Text(
                                text = "${viewModel.roundup(valueIndicatorHumidity)}%",
                                style = TextStyle(
                                    fontSize = 35.sp,
                                    lineHeight = 28.sp,
                                    //fontFamily = FontFamily(Font(R.font.roboto)),
                                    fontWeight = FontWeight(400),

                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .padding(top = 9.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    ElevatedCard(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.outlineVariant),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp),
                        modifier = Modifier
                            .width(138.dp)
                            .height(118.dp)
                            .background(
                                color = Color(0xFF83B4FF),
                                shape = RoundedCornerShape(size = 14.dp)
                            )) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.temperature_icon),
                                    contentDescription = "Temperature",
                                    modifier = Modifier.padding()
                                )
                                Text(
                                    text = stringResource(id = R.string.temperature),
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        lineHeight = 24.sp,
                                        //fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(500),

                                        letterSpacing = 0.15.sp,
                                    ),
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                            }
                            Text(
                                text = "${viewModel.roundup(valueIndicatorTemperature)}°C ",
                                style = TextStyle(
                                    fontSize = 35.sp,
                                    lineHeight = 28.sp,
                                    //fontFamily = FontFamily(Font(R.font.roboto)),
                                    fontWeight = FontWeight(400),

                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .padding(top = 9.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }

                    }
                }
            }
        }
    }

}


@Composable
fun CircularIndicator(
    sizeCircular: Float,
    canvasSize: Dp = 300.dp,
    indicatorValue: Double?,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth: Float = 100f
) {
    var allowedIndicatorValue by remember {
        mutableIntStateOf(maxIndicatorValue)
    }
    if (indicatorValue != null) {
        allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
            indicatorValue.toInt()
        } else {
            maxIndicatorValue
        }
    }

    var animatedIndicatorValue by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage =
        (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000), label = ""
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / sizeCircular
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}
/*
@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}*/


@Preview(showBackground = true)
@Composable
fun MonitorScreenPreview() {
    MonitorsScreen(userData = UserData(userId = "",username = "",profilePictureUrl = null), onSignOut = {})
}
@Composable
@Preview(showBackground = true)
fun CircularIndicatorPreview() {
    //CircularIndicator(sizeCircular = 1.5f)
}