package com.example.tokobunda.Model

object Constants {
    //db_name
    const val DB_NAME = "DB_CATATAN"
    //db_version
    const val DB_VERSION = 1

    //table name (Barang)
    const val TABLE_BARANG = "TABLE_BARANG"
    //columns//field of table (baranag)
    const val ID = "ID"
    const val NAMA_BARANG= "NAMA_BARANG"
    const val JUMLAH_BARANG = "JUMLAH_BARANG"
    const val HARGA= "HARGA"
    const val ADDED_TIMESTAMP = "ADDED_TIMESTAMP"
    const val UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP"

    //Create table query (barang)
    const val CREATE_TABLE_BARANG = (
            "CREATE TABLE " + TABLE_BARANG + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NAMA_BARANG + " TEXT,"
                    + JUMLAH_BARANG+ " TEXT,"
                    + HARGA + " TEXT,"
                    + ADDED_TIMESTAMP + " TEXT,"
                    + UPDATED_TIMESTAMP + " TEXT"
                    + ")"
            )

    //table name (penjualan)
    const val TABLE_PENJUALAN = "TABLE_PENJUALAN"
    //columns//field of table (Penjualan)
    const val C_ID = "ID"
    const val C_NAMA_TERJUAL = "NAMA_TERJUAL"
    const val C_JUMLAH_TERJUAL = "JUMLAH_TERJUAL"
    const val C_ADDED_TIMESTAMP = "ADDED_TIMESTAMP"
    const val C_UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP"

    //Create table query (penjualan)
    const val CREATE_TABLE_PENJUALAN = (
            "CREATE TABLE " + TABLE_PENJUALAN + "("
                    + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + C_NAMA_TERJUAL + " TEXT,"
                    + C_JUMLAH_TERJUAL + " TEXT,"
                    + C_ADDED_TIMESTAMP + " TEXT,"
                    + C_UPDATED_TIMESTAMP + " TEXT"
                    + ")"
            )
}