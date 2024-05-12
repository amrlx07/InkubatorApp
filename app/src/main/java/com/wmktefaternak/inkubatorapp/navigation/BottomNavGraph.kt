package com.wmktefaternak.inkubatorapp.navigation


import android.os.Build

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wmktefaternak.inkubatorapp.authgoogle.UserData
import com.wmktefaternak.inkubatorapp.screen.ControlScreen
import com.wmktefaternak.inkubatorapp.screen.FormScreen
import com.wmktefaternak.inkubatorapp.screen.MonitorsScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController, userData: UserData?,onSignOut: () -> Unit) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Monitoring.route) {

        composable(route = BottomBarScreen.Monitoring.route){
            MonitorsScreen(
                userData = userData,
                onSignOut = onSignOut)
        }
        composable(route = BottomBarScreen.Controlling.route){
            ControlScreen()
        }
        composable(route = BottomBarScreen.Form.route){
            FormScreen()
        }
    }
}