package com.example.tokobunda.Dashnoard.Penjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.tokobunda.Dashnoard.Barang.BarangActivity
import com.example.tokobunda.Model.DbHelper
import com.example.tokobunda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddUpdateRecordPenjualanActivity : AppCompatActivity() {
    //variabel that will contain data to save database
    private var id: String? = ""
    private var nama: String? = ""
    private var jumlah: String? = ""
    private var addedTime: String? = ""
    private var updatedTime: String? = ""

    private var isEditMode = false

    //actionbar
    private var actionBar: ActionBar? = null;

    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_record_penjualan)

        //init actionbar
        actionBar = supportActionBar
        //title actionbar
        actionBar!!.title = "Tambah Data Penjualan"
        //back button in actionbar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //get data from intent
        val intent = intent
        isEditMode = intent.getBooleanExtra("isEditMode", false)
        if (isEditMode) {
            //editing data, came gere from adapter
            actionBar!!.title = "Update Data Penjualan"

            id = intent.getStringExtra("C_ID")
            nama = intent.getStringExtra("C_NAMA_TERJUAL")
            jumlah = intent.getStringExtra("C_JUMLAH_TERJUAL")
            addedTime = intent.getStringExtra("C_ADDED_TIME")
            updatedTime = intent.getStringExtra("C_UPDATED_TIME")

            findViewById<EditText>(R.id.nameterjualEpt).setText(nama)
            findViewById<EditText>(R.id.jumlahTerjualEtp).setText(jumlah)


        } else {
            //adding new data, came here from MainActivity
            actionBar!!.title = "Tambah Data Penjualan"

        }


        //init db helper class
        dbHelper = DbHelper(this)
        //click savebtn to save record
        findViewById<FloatingActionButton>(R.id.saveBtnpTerjual).setOnClickListener {
            inputData()
            startActivity(Intent(this, PenjualanActivity::class.java))
        }

    }

    private fun inputData() {
        //get data
        nama = "" + findViewById<EditText>(R.id.nameterjualEpt).text.toString().trim()
        jumlah = "" + findViewById<EditText>(R.id.jumlahTerjualEtp).text.toString().trim()

        if (isEditMode) {
            // editing
            val timeStamp = "${System.currentTimeMillis()}"
            dbHelper?.updateRecordPenjualan(
                "$id",
                "$nama",
                "$jumlah",
                "$timeStamp",
                "$timeStamp")

            Toast.makeText(this, "Updated... ID $id", Toast.LENGTH_SHORT).show()
        } else { // adding new

            //save data to db
            val timestamp = System.currentTimeMillis()
            val id = dbHelper.insertRecordPenjualan(
                "" + nama,
                "" + jumlah,
                "" + timestamp,
                "" + timestamp)
            Toast.makeText(this, "Record Added against ID $id", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, PenjualanActivity::class.java))
        return super.onSupportNavigateUp()
    }

}
