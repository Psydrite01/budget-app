package com.example.budgetapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [monthlybudget::class],
    version = 1,
    exportSchema = false
)
abstract class budgetdatabase: RoomDatabase() {
    abstract fun budget_dao() : budgetDAO
}

@Database(
    entities = [transaction::class],
    version = 1,
    exportSchema = false
)
abstract class transacdatabase:RoomDatabase() {
    abstract fun transac_dao() : transacDAO
}