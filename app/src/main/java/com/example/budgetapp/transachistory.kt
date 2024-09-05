package com.example.budgetapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetapp.data.datatransferobject
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.transaction

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun transachistory(){
    val theviewmodel: mainviewmodel = viewModel()
    val thehistory = theviewmodel.gettransac.collectAsState(initial = listOf())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
        ){
            items(thehistory.value){
                if (it.isupcoming==false){
                    showtransac(it)
                }
            }
        }
    }
}

@Composable
fun showtransac(transaction: transaction){
    var tempchar:Char = if (transaction.isGaining) '+' else '-'

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, color = Color.Blue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(text = " $tempchar ${transaction.amount}, ${transaction.notes}")
            if (transaction.isupcoming==false){
                Text(text = "To: ${transaction.parentdata}")
                Text(text = "Time: ${transaction.date}")
            }
        }
    }
}