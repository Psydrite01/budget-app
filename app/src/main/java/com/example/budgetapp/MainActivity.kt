package com.example.budgetapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.ui.theme.BudgetAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { navbar()},
                    content = {
                        Box(modifier = Modifier
                            .padding(paddingValues = it)
                        ){
                            appnavigation(navctrl = navController)
                        }
                    },
                    bottomBar = { bottombar(
                        gotohome = {navController.navigate("home")},
                        gotohistory = {navController.navigate("history")},
                        gotoarchive = {navController.navigate("archive")}
                    )},
                    floatingActionButton = { addbutton() },
                    modifier = Modifier.fillMaxSize())
            }
        }
    }
}
