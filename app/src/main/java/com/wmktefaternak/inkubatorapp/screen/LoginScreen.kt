package com.wmktefaternak.inkubatorapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wmktefaternak.inkubatorapp.R
import com.wmktefaternak.inkubatorapp.authgoogle.SignInState


@Composable
fun LoginScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.login_welcome),
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 20.sp,
            ),
            modifier = Modifier
                .height(25.dp)
                .align(Alignment.Start)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(id = R.string.login_detail),
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 20.sp,
            ),
            modifier = Modifier
                .height(40.dp)
                .align(Alignment.Start)


        )
        Spacer(modifier = Modifier.height(442.dp))
        OutlinedButton(
            onClick =  onSignInClick,
            shape = RoundedCornerShape(size = 10.dp)
        ) {
            Row {
                Icon(painter = painterResource(id = R.drawable.flat_color_icons_google), contentDescription = null, modifier = Modifier
                    .padding(end = 10.dp)
                    .width(18.dp)
                    .height(18.dp))
                Text(text = stringResource(id = R.string.login_button))

            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        ) {
            Divider(modifier = Modifier
                .width(100.dp)
                .padding(end = 20.dp))
            Text(text = "Atau")
            Divider(modifier = Modifier
                .width(100.dp)
                .padding(start = 20.dp))
        }
        Button(onClick = {
            Toast.makeText(
                context,
                "Belum Tersedia",
                Toast.LENGTH_LONG
            ).show()
        },
            shape = RoundedCornerShape(size = 10.dp)) {
            Text(text = "Masukan Dengan Opsi Lain")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    //LoginScreen()
}