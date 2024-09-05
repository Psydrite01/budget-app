package com.example.budgetapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetapp.data.datatransferobject
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.monthlybudget

@Composable
fun deletemonthprompt(monthlybudget: monthlybudget,gotoparent:()->Unit){
    var isradioselected:Boolean by remember { mutableStateOf(false) }
    var theviewmodel:mainviewmodel = viewModel()
    val thehistory = theviewmodel.gettransac.collectAsState(initial = listOf())
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Alert")
        Text(text = "All the data and transaction history related to the month will be deleted permanently and cannot be recovered")
        Row {
            RadioButton(
                selected = isradioselected,
                onClick = { isradioselected=!isradioselected })
            Text(text = "Confirm")
        }
        Row {
            Button(
                onClick = {
                    isradioselected=false
                    datatransferobject.isdeleteprompt=false
                    gotoparent()
                }) {
                Text(text = "Cancel")
            }
            Button(
                onClick = {
                    if(isradioselected){
                        theviewmodel.deletemonth(monthlybudget)
                        deletemonthhistory(monthlybudget,theviewmodel,thehistory.value)
                        isradioselected=false
                        datatransferobject.isdeleteprompt=false
                        gotoparent()
                    }
                }) {
                Text(text = "Delete")
            }
        }
    }
}