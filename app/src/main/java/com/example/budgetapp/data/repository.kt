package com.example.budgetapp.data

import kotlinx.coroutines.flow.Flow


class repository(private val budgetDAO: budgetDAO) {
    fun getAll():Flow<List<monthlybudget>> = budgetDAO.getAll()

    suspend fun newmonth(monthlybudget: monthlybudget){
        budgetDAO.addmonth(monthlybudget)
    }

    suspend fun updatemonth(monthlybudget: monthlybudget){
        budgetDAO.updatemonth(monthlybudget)
    }

    suspend fun deletemonth(monthlybudget: monthlybudget){
        budgetDAO.deletemonth(monthlybudget)
    }
}

class repository_transac(private val transacDAO: transacDAO){
    fun getAll():Flow<List<transaction>> = transacDAO.getAll()

    suspend fun newtransac(transaction: transaction){
        transacDAO.newtransac(transaction)
    }

    suspend fun deletetransac(transaction: transaction){
        transacDAO.deletetransac(transaction)
    }
}