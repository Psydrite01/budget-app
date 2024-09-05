package com.example.budgetapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class mainviewmodel(
    private val budget_repo:repository = budget_graph.getfromrepository,
    private val transac_repo:repository_transac = transac_graph.getfromtransacrepo
):ViewModel(){
    lateinit var getmonths: Flow<List<monthlybudget>>
    lateinit var gettransac: Flow<List<transaction>>


    init {
        viewModelScope.launch {
            getmonths = budget_repo.getAll()
            gettransac = transac_repo.getAll()
        }
    }

    fun newmonth(monthlybudget: monthlybudget){
        viewModelScope.launch(Dispatchers.IO) {
            budget_repo.newmonth(monthlybudget)
        }
    }

    fun updatemonth(monthlybudget: monthlybudget){
        viewModelScope.launch(Dispatchers.IO) {
            budget_repo.updatemonth(monthlybudget)
        }
    }

    fun deletemonth(monthlybudget: monthlybudget){
        viewModelScope.launch(Dispatchers.IO) {
            budget_repo.deletemonth(monthlybudget)
        }
    }

    fun newtransac(transaction: transaction){
        viewModelScope.launch(Dispatchers.IO) {
            transac_repo.newtransac(transaction)
        }
    }

    fun deletetransac(transaction: transaction){
        viewModelScope.launch(Dispatchers.IO) {
            transac_repo.deletetransac(transaction)
        }
    }
}