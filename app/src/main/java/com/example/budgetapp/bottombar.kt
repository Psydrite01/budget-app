package com.example.budgetapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun bottombar(gotohome:()->Unit,gotohistory:()->Unit,gotoarchive:()->Unit){
    Divider(
        modifier = Modifier
            .fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, top = 15.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Home",
            modifier = Modifier
                .clickable {
                    gotohome()
                })
        Text(text = "History",
            modifier = Modifier
                .clickable {
                    gotohistory()
                })
        Text(text = "Archived",
            modifier = Modifier
                .clickable {
                    gotoarchive()
                })
    }
}