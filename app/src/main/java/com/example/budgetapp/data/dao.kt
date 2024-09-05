package com.example.budgetapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class budgetDAO {
    @Query("SELECT * from 'budget_db'")
    abstract fun getAll(): Flow<List<monthlybudget>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addmonth(monthlybudget: monthlybudget)

    @Update
    abstract suspend fun updatemonth(monthlybudget: monthlybudget)

    @Delete
    abstract suspend fun deletemonth(monthlybudget: monthlybudget)
}


@Dao
abstract class transacDAO {
    @Query("SELECT * from 'transaction_history'")
    abstract fun getAll():Flow<List<transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun newtransac(transaction: transaction)

    @Delete
    abstract suspend fun deletetransac(transaction: transaction)
}