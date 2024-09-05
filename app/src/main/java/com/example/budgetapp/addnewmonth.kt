package com.example.budgetapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetapp.data.datatransferobject
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.monthlybudget

@Composable
fun addmonth(){
    val months = listOf(
        "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY",
        "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
    )
    val years = listOf(
        "2020", "2021", "2022", "2023", "2024",
        "2025", "2026", "2027", "2028", "2029"
    )
    var selectedmonth by remember { mutableStateOf(months[0]) }
    var selectedyear by remember { mutableStateOf(years[4]) }

    var isexpanded_month by remember { mutableStateOf(false) }
    var isexpanded_year by remember { mutableStateOf(false) }

    val theviewmodel:mainviewmodel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray)
    ) {
        Text(text = "this is a text inside addmonth")
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.Blue)
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(4.dp),
                text = selectedmonth
            )
            Button(
                onClick = { isexpanded_month = !isexpanded_month }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "month")
            }
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = isexpanded_month,
            onDismissRequest = {
                isexpanded_month = false
            }) {
            months.forEach {
                DropdownMenuItem(
                    text = {
                           Text(text = it)
                    },
                    onClick = {
                        selectedmonth = it
                        isexpanded_month = false
                    })
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.Blue)
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(4.dp),
                text = selectedyear
            )
            Button(
                onClick = { isexpanded_year = !isexpanded_year }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "year")
            }
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = isexpanded_year,
            onDismissRequest = {
                isexpanded_year = false
            }) {
            years.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick = {
                        selectedyear = it
                        isexpanded_year = false
                    })
            }
        }
        Text(text = "Note: If the specific month and year already exists, then creation will fail. Please delete existing data to create a new entry")
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = {
                    datatransferobject.isaddmonth = false
                }
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = {
                    theviewmodel.newmonth(monthlybudget("$selectedmonth, $selectedyear"))
                    datatransferobject.isaddmonth = false
                }
            ) {
                Text(text = "Confirm")
            }
        }
    }
}