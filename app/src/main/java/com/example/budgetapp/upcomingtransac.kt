package com.example.budgetapp

import android.os.Build
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetapp.data.datatransferobject
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.monthlybudget
import com.example.budgetapp.data.transaction
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun upcomingtransaction(monthlybudget: monthlybudget,gotoparent:()->Unit){
    var viewmodel: mainviewmodel= viewModel()

    var amount:String by remember { mutableStateOf("") }
    var notes:String by remember { mutableStateOf("") }
    var date:String by remember { mutableStateOf("") }
    var isgaining:Boolean by remember { mutableStateOf(false) }

    var updatedmonth = monthlybudget

    var title:String = "Add ${datatransferobject.newtransacdata.type}"
    var isdropdownactive:Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
    ) {
        Text(text = "Upcoming Transaction")

        Row {
            Text(text = "Select Type: ${datatransferobject.newtransacdata.type}")
            Button(
                onClick = { isdropdownactive=true }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null)
            }
            DropdownMenu(
                expanded = isdropdownactive,
                onDismissRequest = {
                    isdropdownactive=false
                }) {
                DropdownMenuItem(
                    text = { Text(text = "Allowance") },
                    onClick = {
                        datatransferobject.newtransacdata.type="allowance"
                        isdropdownactive=false
                        isgaining=true
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Necessities") },
                    onClick = {
                        datatransferobject.newtransacdata.type="necessities"
                        isdropdownactive=false
                        isgaining=false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Extras") },
                    onClick = {
                        datatransferobject.newtransacdata.type="extras"
                        isdropdownactive=false
                        isgaining=false
                    }
                )
            }
        }
        OutlinedTextField(
            value = amount,
            onValueChange = {
                amount = it
            }, placeholder = {Text(text = "Amount")})
        OutlinedTextField(
            value = notes,
            onValueChange = {
                notes = it
            }, placeholder = {Text(text = "Add Notes")})
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = {
                    updatedmonth.isupcomingtransac=false
                    viewmodel.updatemonth(updatedmonth)
                    gotoparent()
                }
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = {
                    if(amount != null){
                        var newtransac = datatransferobject.newtransacdata
//                        newtransac.parentdata=updatedmonth.monthyeardata
                        newtransac.amount = amount.toFloat()
                        newtransac.isGaining = isgaining
                        newtransac.notes = notes
                        newtransac.isupcoming=true

                        viewmodel.newtransac(newtransac)
                        when (datatransferobject.newtransacdata.type){
                            "allowance"->{
//                                updatedmonth.allowance = updatedmonth.allowance?.plus(amount.toFloat())
                                updatedmonth.appbalance = updatedmonth.appbalance?.plus(amount.toFloat())}
                            "necessities"->{
//                                updatedmonth.necessities = updatedmonth.necessities?.minus(amount.toFloat())
                                updatedmonth.appbalance = updatedmonth.appbalance?.minus(amount.toFloat())
//                                updatedmonth.total = updatedmonth.total?.plus(amount.toFloat())
                            }
                            "extras"->{
//                                updatedmonth.extras = updatedmonth.extras?.minus(amount.toFloat())
                                updatedmonth.appbalance = updatedmonth.appbalance?.minus(amount.toFloat())
//                                updatedmonth.total = updatedmonth.total?.plus(amount.toFloat())
                            }
                        }
                        viewmodel.updatemonth(updatedmonth)
                    }
                    updatedmonth.isupcomingtransac=false
                    viewmodel.updatemonth(updatedmonth)
                    datatransferobject.isnewtransac = false
                    gotoparent()
                }
            ) {
                Text(text = "Confirm")
            }
        }
    }
}