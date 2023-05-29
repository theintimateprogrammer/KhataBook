package com.hkinfo.khatabook.Models

class TransModel {

    var id = 0
    var amt = 0
    lateinit var category: String
    lateinit var note: String
    var isExpense = 0
    lateinit var time:String
    var date = 0
    var month = 0
    var year = 0


    constructor(
        id: Int,
        amt: Int,
        category: String,
        note: String,
        isExpense: Int,
        time: String,
        date: Int,
        month: Int,
        year: Int
    ) {
        this.id = id
        this.amt = amt
        this.category = category
        this.note = note
        this.isExpense = isExpense
        this.time = time
        this.date = date
        this.month = month
        this.year = year
    }
}