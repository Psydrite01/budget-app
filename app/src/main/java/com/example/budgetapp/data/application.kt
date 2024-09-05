package com.example.budgetapp.data

import android.app.Application

class budgetapplication:Application() {
    override fun onCreate() {
        super.onCreate()
        budget_graph.builddatabase(context = this)
        transac_graph.buildtransacdatabase(context = this)
    }
}