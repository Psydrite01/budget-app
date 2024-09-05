package com.example.budgetapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

object datatransferobject {
    var isaddmonth:Boolean by mutableStateOf(false)
    var isnewtransac:Boolean by mutableStateOf(false)
    var newtransacdata:transaction by mutableStateOf(transaction(0.0f,"","","","",false))
    var isdeleteprompt:Boolean by mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.O)
    var currentdate:LocalDate = LocalDate.now()
}

