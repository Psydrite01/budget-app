package com.example.budgetapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetapp.data.datatransferobject
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.monthlybudget
import com.example.budgetapp.data.transaction

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun archieve(gotoarchive:()->Unit){
    val theviewmodel:mainviewmodel= viewModel()
    val thebudgetlist = theviewmodel.getmonths.collectAsState(initial = listOf())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "this is a text inside archieve")
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
        ){
            items(thebudgetlist.value){
                if (it.isarchieved==true){
                    showmonth(it,theviewmodel,gotoarchive)
                }
            }
        }
    }
    if(datatransferobject.isaddmonth) {
        AlertDialog(
            onDismissRequest = {
                datatransferobject.isaddmonth = false
            }
        ) {
            addmonth()
        }
    }
}