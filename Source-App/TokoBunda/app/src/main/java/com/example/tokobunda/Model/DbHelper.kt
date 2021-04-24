package com.example.tokobunda.Model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(context: Context?): SQLiteOpenHelper(
        context,
        Constants.DB_NAME,
        null,
        Constants.DB_VERSION
){
    override fun onCreate(db: SQLiteDatabase) {
        //create table on that db
        db.execSQL(Constants.CREATE_TABLE_BARANG)
        db.execSQL(Constants.CREATE_TABLE_PENJUALAN)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //upgrade database(if there is any structure change, change db version)
        //drop oldere table if exists

        db.execSQL("DROP TABLE IF EXISTS"+ Constants.TABLE_BARANG)
        db.execSQL("DROP TABLE IF EXISTS"+ Constants.TABLE_PENJUALAN)
        onCreate(db)
    }

  //  insert record to db
    fun insertRecordPenjualan(
            nama:String?,
            jumlah:String?,
            addedTime:String?,
            updatedTime:String?
    ):Long{
        //get wirtable database because we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        //id will be inserted automatically as we set AUTOINCREMENT in query
        //insert data
        values.put(Constants.C_NAMA_TERJUAL, nama)
        values.put(Constants.C_JUMLAH_TERJUAL, jumlah)
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime)
        values.put(Constants.C_UPDATED_TIMESTAMP, updatedTime)

        //insert row, it will return record id of saved record
        val id = db.insert(Constants.TABLE_PENJUALAN, null, values)
        //close db connection
        db.close()
        //return id of inserted record
        return id

    }


    //insert record to db
    fun insertRecordBarang(
            nama:String?,
            jumlah:String?,
            harga:String?,
            addedTime:String?,
            updatedTime:String?
    ):Long{
        //get wirtable database because we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        //id will be inserted automatically as we set AUTOINCREMENT in query
        //insert data
        values.put(Constants.NAMA_BARANG, nama)
        values.put(Constants.JUMLAH_BARANG, jumlah)
        values.put(Constants.HARGA, harga)
        values.put(Constants.ADDED_TIMESTAMP, addedTime)
        values.put(Constants.UPDATED_TIMESTAMP, updatedTime)

        //insert row, it will return record id of saved record
        val id = db.insert(Constants.TABLE_BARANG, null, values)
        //close db connection
        db.close()
        //return id of inserted record
        return id

    }

    @SuppressLint("Recycle")
    fun getAllRecordsPenjualan(orderBy:String):ArrayList<ModelRecordPenjualan>{

        val recordList = ArrayList<ModelRecordPenjualan>()

        val selectQuery = "SELECT * FROM ${Constants.TABLE_PENJUALAN} ORDER BY $orderBy"
        val db = this.writableDatabase
        val cunsor = db.rawQuery(selectQuery, null)

        if (cunsor.moveToFirst()){
            do {
                val modelRecord = ModelRecordPenjualan(
                    ""+cunsor.getInt(cunsor.getColumnIndex(Constants.C_ID)),
                    ""+cunsor.getString(cunsor.getColumnIndex(Constants.C_NAMA_TERJUAL)),
                    ""+cunsor.getString(cunsor.getColumnIndex(Constants.C_JUMLAH_TERJUAL)),
                    ""+cunsor.getString(cunsor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                    ""+cunsor.getString(cunsor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP))
                )
                //add record to list
                recordList.add(modelRecord)
            }while (cunsor.moveToNext())
        }
        //close db connection
        db.close()
        //reteurn the queried result list
        return recordList
    }


    //get all data barang
    @SuppressLint("Recycle")
    fun getAllRecordsBarang(orderBy:String):ArrayList<ModelRecordBarang>{

        val recordList = ArrayList<ModelRecordBarang>()

        val selectQuery = "SELECT * FROM ${Constants.TABLE_BARANG} ORDER BY $orderBy"
        val db = this.writableDatabase
        val cunsor = db.rawQuery(selectQuery, null)

        if (cunsor.moveToFirst()){
            do {
                val modelRecord = ModelRecordBarang(
                        ""+cunsor.getInt(cunsor.getColumnIndex(Constants.ID)),
                        ""+cunsor.getString(cunsor.getColumnIndex(Constants.NAMA_BARANG)),
                        ""+cunsor.getString(cunsor.getColumnIndex(Constants.JUMLAH_BARANG)),
                        ""+cunsor.getString(cunsor.getColumnIndex(Constants.HARGA)),
                        ""+cunsor.getString(cunsor.getColumnIndex(Constants.ADDED_TIMESTAMP)),
                        ""+cunsor.getString(cunsor.getColumnIndex(Constants.UPDATED_TIMESTAMP))
                )
                //add record to list
                recordList.add(modelRecord)
            }while (cunsor.moveToNext())
        }
        //close db connection
        db.close()
        //reteurn the queried result list
        return recordList
    }
    //delete (single) record using record id penjualan
    fun deleteRecordPenjualan(id:String){
        val db = writableDatabase
        db.delete(
                Constants.TABLE_PENJUALAN,
                "${Constants.C_ID}=?",
                arrayOf(id)
        )
        db.close()
    }

    //delete (single) record using record id (Barang)
    fun deleteRecordBarang(id:String){
        val db = writableDatabase
        db.delete(
                Constants.TABLE_BARANG,
                "${Constants.ID}=?",
                arrayOf(id)
        )
        db.close()
    }

    //update record to db penjualan
    fun updateRecordPenjualan(
            id: String,
            nama: String,
            jumlah: String,
            addedTime: String,
            updatedTime: String
    ): Long{
        //get writeable database
        val db = this.writableDatabase
        val values = ContentValues()

        //insert data to update
        values.put(Constants.C_NAMA_TERJUAL, nama)
        values.put(Constants.C_JUMLAH_TERJUAL, jumlah)
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime)
        values.put(Constants.C_UPDATED_TIMESTAMP, updatedTime)

        //update
        return db.update(Constants.TABLE_PENJUALAN,
                values,
                "${Constants.C_ID}=?",
                arrayOf(id)).toLong()

    }

    //update record to db barang
    fun updateRecordBaranag(
            id: String,
            nama: String,
            jumlah: String,
            harga: String,
            addedTime: String,
            updatedTime: String
    ): Long{
        //get writeable database
        val db = this.writableDatabase
        val values = ContentValues()

        //insert data to update
        values.put(Constants.NAMA_BARANG, nama)
        values.put(Constants.JUMLAH_BARANG, jumlah)
        values.put(Constants.HARGA, harga)
        values.put(Constants.ADDED_TIMESTAMP, addedTime)
        values.put(Constants.UPDATED_TIMESTAMP, updatedTime)

        //update
        return db.update(Constants.TABLE_BARANG,
                values,
                "${Constants.ID}=?",
                arrayOf(id)).toLong()

    }



}







