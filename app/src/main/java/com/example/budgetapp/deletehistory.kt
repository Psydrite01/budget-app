package com.example.budgetapp

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.budgetapp.data.mainviewmodel
import com.example.budgetapp.data.monthlybudget
import com.example.budgetapp.data.transaction

fun deletemonthhistory(monthlybudget: monthlybudget, viewmodel:mainviewmodel, thehistory:List<transaction>){
    var tempstr = monthlybudget.monthyeardata
    for (i in thehistory){
        if (i.parentdata==tempstr){
            viewmodel.deletetransac(i)
        }
    }
}