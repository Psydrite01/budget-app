package com.example.budgetapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.budgetapp.data.datatransferobject
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.monthlybudget
import java.time.LocalDate
import kotlin.math.round


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showmonth(data: monthlybudget, theviewmodel: mainviewmodel, gotoparent:()->Unit){
    var newmonth=data
    var upcominglist = theviewmodel.gettransac.collectAsState(initial = listOf())
    var isdropdownactive:Boolean by remember { mutableStateOf(false) }
    var temptext:String = if (data.isarchieved){"Unarchive"} else {"Archive"}
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, color = Color.Blue)
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "${data.monthyeardata}")
            Column {
                Button(onClick = {
                    isdropdownactive= !isdropdownactive
                }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null)
                }
                DropdownMenu(
                    expanded = isdropdownactive,
                    onDismissRequest = {
                        isdropdownactive = false
                    }) {
                    DropdownMenuItem(
                        text = { Text(text = temptext) },
                        onClick = {
                            newmonth.isarchieved= !newmonth.isarchieved
                            theviewmodel.updatemonth(newmonth)
                            gotoparent()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Delete") },
                        onClick = {
                            newmonth.isdeleting=true
                            theviewmodel.updatemonth(newmonth)
                            gotoparent()
                        })
                }
            }
        }
        Row {
            Text(text = "Allowance: ${data.allowance}")
            Button(
                onClick = {
                    newmonth.isnewtransac=true
                    theviewmodel.updatemonth(newmonth)
                    datatransferobject.newtransacdata.type = "allowance"
                    gotoparent()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null)
            }
        }
        Row {
            Text(text = "Necessities: ${data.necessities}")
            Button(
                onClick = {
                    newmonth.isnewtransac=true
                    theviewmodel.updatemonth(newmonth)
                    datatransferobject.newtransacdata.type = "necessities"
                    gotoparent()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null)
            }
        }
        Row {
            Text(text = "Extras: ${data.extras}")
            Button(
                onClick = {
                    newmonth.isnewtransac=true
                    theviewmodel.updatemonth(newmonth)
                    datatransferobject.newtransacdata.type = "extras"
                    gotoparent()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null)
            }
        }
        Row {
            Text(text = "Total spent: ${data.total}")
        }
        Row {
            Text(text = "Bal: ${data.balance}")
        }
        var currentdate = LocalDate.now()
        if (data.monthyeardata == "${currentdate.month}, ${currentdate.year}"){
            Row {
                Text(text = "Average for rest of The Month:${givedailyexpense(data.balance?:0.0f,currentdate)} /day")
            }
            Divider(
                modifier = Modifier
                    .padding(5.dp)
            )
            Row {
                Text(text = "Upcoming Transactions")
                Button(
                    onClick = {
                        newmonth.isupcomingtransac=true
                        theviewmodel.updatemonth(newmonth)
                        datatransferobject.newtransacdata.type = "allowance"
                        gotoparent()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null)
                }
            }
            for (it in upcominglist.value){
                if (it.isupcoming){
                    showtransac(transaction = it)
                }
            }
//            LazyColumn (
//
//            ){
//                items(upcominglist.value){
//                    showtransac(transaction = it)
//                }
//            }
            Row {
                Text(text = "Approximate Bal: ${data.appbalance}")
            }
            Row {
                Text(text = "App. Av. for rest of the Month: ${givedailyexpense(data.appbalance?:0.0f,currentdate)} /day")
            }
        }
    }
    if(data.isnewtransac) {
        AlertDialog(
            onDismissRequest = {
                newmonth.isnewtransac=false
                theviewmodel.updatemonth(newmonth)
            }
        ) {
            newtransaction(monthlybudget = data,gotoparent)
        }
    }
    if (data.isdeleting) {
        AlertDialog(onDismissRequest = {
            newmonth.isdeleting=false
            theviewmodel.updatemonth(newmonth)
        }) {
            deletemonthprompt(monthlybudget = data,gotoparent)
        }
    }
    if (data.isupcomingtransac){
        AlertDialog(
            onDismissRequest = {
                newmonth.isupcomingtransac=false
                theviewmodel.updatemonth(newmonth)
            }) {
            upcomingtransaction(monthlybudget = data,gotoparent)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun givedailyexpense(bal:Float,currentdate:LocalDate):Float{
    var date = currentdate.dayOfMonth
    var count=0
    while (date <= currentdate.month.maxLength()){
        date += 1
        count+=1
    }
    var perday = bal/count.toFloat()
    perday = round(perday*100)
    return perday/100
}