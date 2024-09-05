package com.example.budgetapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.example.budgetapp.data.datatransferobject

@Composable
fun addbutton(){
    Button(
        onClick = {
           datatransferobject.isaddmonth = true
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add")
    }
}