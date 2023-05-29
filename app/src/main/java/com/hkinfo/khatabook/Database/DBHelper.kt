package com.hkinfo.khatabook.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.hkinfo.khatabook.Models.TransModel

class DBHelper(
    context: Context?
) : SQLiteOpenHelper(context, "Khatabook.db", null, 2) {

    var TABLE_NAME = "transaction"

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql =
            "CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT, amt INTEGER, category TEXT, note TEXT, isexpense INTEGER, times TEXT, dates INTEGER, months INTEGER, years INTEGER)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addAmount(transModel: TransModel) {
        var db = writableDatabase

        var values = ContentValues().apply {
            transModel.apply {
                put("amt", amt)
                put("category", category)
                put("note", note)
                put("isexpense", isExpense)
                put("times", time)
                put("dates", date)
                put("months", month)
                put("years", year)

            }
        }
        db.insert(TABLE_NAME, null, values)
    }

    fun getTransaction(): ArrayList<TransModel> {
        var transList = ArrayList<TransModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM $TABLE_NAME"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count - 1) {

            var id = cursor.getInt(0)
            var amt = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isExpense = cursor.getInt(4)
            var time = cursor.getString(5)
            var date = cursor.getInt(6)
            var month = cursor.getInt(7)
            var year = cursor.getInt(8)

            var model = TransModel(id, amt, category, note, isExpense,time, date, month, year)
            transList.add(model)
            cursor.moveToNext()
        }
        return transList

    }

}