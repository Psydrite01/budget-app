package com.example.budgetapp.data

import android.content.Context
import androidx.room.Room

object budget_graph {
    lateinit var thedatabase: budgetdatabase
    val getfromrepository by lazy {
        repository(budgetDAO= thedatabase.budget_dao())
    }

    fun builddatabase(context: Context){
        thedatabase = Room.databaseBuilder(
            context,
            budgetdatabase::class.java,
            "budget_db"
        ).build()
    }
}

object transac_graph {
    lateinit var transac_database: transacdatabase
    val getfromtransacrepo by lazy {
        repository_transac(transacDAO = transac_database.transac_dao())
    }

    fun buildtransacdatabase(context: Context){
        transac_database = Room.databaseBuilder(
            context,
            transacdatabase::class.java,
            "transaction_db"
        ).build()
    }
}