package com.example.budgetapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun appnavigation(navctrl:NavHostController){
    var nav_controller = navctrl
    NavHost(
        navController = nav_controller,
        startDestination = "home"
    ){
        composable("home"){
            home(
                gotohome = {navctrl.navigate("home")}
            )
        }
        composable("history"){
            transachistory()
        }
        composable("archive"){
            archieve(
                gotoarchive = {navctrl.navigate("archive")}
            )
        }
    }
}