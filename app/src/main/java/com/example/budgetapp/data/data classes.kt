package com.example.budgetapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Year

@Entity(tableName = "budget_db")
data class monthlybudget(
    @PrimaryKey(autoGenerate = false)
    var monthyeardata:String,

    @ColumnInfo(name = "allowance")
    var allowance:Float?=0f,

    @ColumnInfo(name = "necessities")
    var necessities:Float?=0f,

    @ColumnInfo(name = "extras")
    var extras:Float?=0f,

    @ColumnInfo(name = "total")
    var total:Float?=0f,

    @ColumnInfo(name = "balance")
    var balance:Float?=0f,

    @ColumnInfo(name = "appbalance")
    var appbalance:Float?=0f,

    @ColumnInfo(name = "isarchieved")
    var isarchieved:Boolean=false,

    @ColumnInfo(name = "isnewtransac")
    var isnewtransac:Boolean=false,

    @ColumnInfo(name = "isupcomingtransac")
    var isupcomingtransac:Boolean=false,

    @ColumnInfo(name = "isediting")
    var isediting:Boolean=false,

    @ColumnInfo(name = "isdeleting")
    var isdeleting:Boolean=false
)


@Entity(tableName = "transaction_history")
data class transaction(
    @ColumnInfo(name = "amount")
    var amount: Float,

    @ColumnInfo(name = "notes")
    var notes:String,

    @ColumnInfo(name = "date")
    var date:String,

    @ColumnInfo(name = "parentdata")
    var parentdata:String,

    @ColumnInfo(name = "type")
    var type:String,

    @ColumnInfo(name = "isgaining")
    var isGaining:Boolean,

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,

    @ColumnInfo(name = "isediting")
    var isediting:Boolean = false,

    @ColumnInfo(name = "isupcoming")
    var isupcoming:Boolean = false
)

