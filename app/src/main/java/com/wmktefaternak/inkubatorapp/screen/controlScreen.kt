package com.wmktefaternak.inkubatorapp.screen


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wmktefaternak.inkubatorapp.R
import com.wmktefaternak.inkubatorapp.viewModel.ControlViewModel

@Composable
fun ControlScreen(viewModel: ControlViewModel = viewModel()) {
    //val viewModel : ControlViewModel = viewModel()\
    val condisionState by viewModel.condision.collectAsStateWithLifecycle()
    val state by viewModel.kipas.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp),
            modifier = Modifier
                .width(350.dp)
                .height(70.dp)
                .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 14.dp))) {
            Text(
                text = stringResource(R.string.Control_incubator),

                // M3/title/large
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    //fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(400),
                ),
                modifier = Modifier.padding(start = 80.dp, top = 21.dp)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp),
            modifier = Modifier
                .width(350.dp)
                .height(100.dp)
                .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 14.dp))
        ) {
            Column(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 15.dp)
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.caution),
                        style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 24.sp,
                            //fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(900),
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
                }
                Text(
                    text = stringResource(R.string.caution_details),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.5.sp,
                        //fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(300),
                        textAlign = TextAlign.Justify,
                        letterSpacing = 0.15.sp,
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp),
            modifier = Modifier
                .width(350.dp)
                .height(495.dp)
                .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 24.dp)),) {
            Column(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                PanelControl(
                    title = stringResource(R.string.fan_title),
                    condision = condisionState.kipas,
                    color =  MaterialTheme.colorScheme.outlineVariant,
                    contentDesc = stringResource(R.string.fan_details),
                    checked = state.isFanOn,
                    onChecked = {isChecked ->
                                viewModel.kipasDevice(isChecked)
                                },
                    icon = R.drawable.fan_control)
                Spacer(modifier = Modifier.height(12.dp))
                PanelControl(
                    color = MaterialTheme.colorScheme.scrim,
                    title = stringResource(R.string.heater_title),
                    condision = condisionState.heater,
                    contentDesc = stringResource(R.string.heater_details),
                    checked = state.isHeaterOn,
                    onChecked = { isChecked ->
                                viewModel.heaterDevice(isChecked)
                                },
                    icon = R.drawable.heater_control
                )
                Spacer(modifier = Modifier.height(12.dp))
                PanelControl(
                    color = MaterialTheme.colorScheme.inversePrimary,
                    title = stringResource(R.string.motor_title),
                    condision = condisionState.motor,
                    contentDesc = stringResource(R.string.motor_details),
                    checked = state.isMotorOn,
                    onChecked = {isChecked ->
                                viewModel.motorDevice(isChecked)


                    },
                    modifier = Modifier.padding(bottom = 10.dp),
                    icon = R.drawable.motor_control
                )

            }
        }
    }
}

@Composable
fun PanelControl(
    @DrawableRes icon: Int,
    title: String,
    condision: Int?,
    color: Color,
    contentDesc: String,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier) {
    var kondisi by remember { mutableStateOf("Mati") }
    if (condision == 1) {
        kondisi = "Hidup"
    } else {
        kondisi = "Mati"
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp),
        modifier = Modifier
            .width(330.dp)
            .height(150.dp)
            .background(color = Color(0xFF83B4FF), shape = RoundedCornerShape(size = 14.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier
                .padding(start = 17.dp, top = 10.dp)
                .width(60.dp)
                .height(77.dp)
                )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxSize()
            ) {
                Text(text = title,
                    style = TextStyle(
                        fontSize = 34.sp,
                        lineHeight = 24.sp,
                        //fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(700),
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(text = contentDesc,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        //fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(300),
                        textAlign = TextAlign.Justify,
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier
                        .width(175.dp)
                        .height(49.dp)
                )
                Row {
                    Text(text = "Kondisi ${title}: ${kondisi}",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                    Switch(
                        checked = checked, onCheckedChange = onChecked,
                        modifier = Modifier
                            .padding(end = 20.dp, bottom = 14.dp, start = 45.dp)

                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun PanelControlPreview() {
    var checked by remember{ mutableStateOf(false) }
    PanelControl(
        title = "Kipas",
        contentDesc = "Kipas digunakan mengatur persebaran udara yang masuk dan udara yang keluar",
        checked = checked, onChecked = {checked = it},
        icon = R.drawable.motor_control,
        condision = 1,
        color =  MaterialTheme.colorScheme.scrim,
    )

}

@Preview(showBackground = true)
@Composable
fun ControlPanelPreview() {
    ControlScreen()
}