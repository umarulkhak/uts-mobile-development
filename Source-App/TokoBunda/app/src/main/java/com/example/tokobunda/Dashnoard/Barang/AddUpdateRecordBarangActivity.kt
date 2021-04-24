package com.example.tokobunda.Dashnoard.Barang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.tokobunda.Model.DbHelper
import com.example.tokobunda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.row_record_barang.*

class AddUpdateRecordBarangActivity : AppCompatActivity() {
    //variabel that will contain data to save database
    private var id: String? = ""
    private var name: String? = ""
    private var jumlah: String? = ""
    private var harga: String? = ""
    private var addedTime: String? = ""
    private var updatedTime: String? = ""

    private var isEditMode = false

    //actionbar
    private var actionBar: ActionBar? = null;

    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_record_barang)

        //init actionbar
        actionBar = supportActionBar
        //title actionbar
        actionBar!!.title = "Tambah Data Barang"
        //back button in actionbar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //get data from intent
        val intent = intent
        isEditMode = intent.getBooleanExtra("isEditMode", false)
        if (isEditMode) {
            //editing data, came gere from adapter
            actionBar!!.title = "Update Data Barang"

            id = intent.getStringExtra("ID")
            name = intent.getStringExtra("NAMA_BARANG")
            jumlah = intent.getStringExtra("JUMLAH_BARANG")
            harga = intent.getStringExtra("HARGA")
            addedTime = intent.getStringExtra("ADDED_TIME")
            updatedTime = intent.getStringExtra("UPDATED_TIME")

            findViewById<EditText>(R.id.nameEpt).setText(name)
            findViewById<EditText>(R.id.jumlahEtp).setText(jumlah)
            findViewById<EditText>(R.id.hargaEpt).setText(harga)


        } else {
            //adding new data, came here from MainActivity
            actionBar!!.title = "Tambah Data Barang"

        }


        //init db helper class
        dbHelper = DbHelper(this)
        //click savebtn to save record
        findViewById<FloatingActionButton>(R.id.saveBtnp).setOnClickListener {
            inputData()
            startActivity(Intent(this, BarangActivity::class.java))
        }

    }

    private fun inputData() {
        //get data
        name = "" + findViewById<EditText>(R.id.nameEpt).text.toString().trim()
        jumlah = "" + findViewById<EditText>(R.id.jumlahEtp).text.toString().trim()
        harga = "" + findViewById<EditText>(R.id.hargaEpt).text.toString().trim()

        if (isEditMode) {
            // editing
            val timeStamp = "${System.currentTimeMillis()}"
            dbHelper?.updateRecordBaranag(
                "$id",
                "$name",
                "$jumlah",
                "$harga",
                "$timeStamp",
                "$timeStamp")

            Toast.makeText(this, "Updated... ID $id", Toast.LENGTH_SHORT).show()
        } else { // adding new

            //save data to db
            val timestamp = System.currentTimeMillis()
            val id = dbHelper.insertRecordBarang(
                "" + name,
                "" + jumlah,
                "" + harga,
                "" + timestamp,
                "" + timestamp)
            Toast.makeText(this, "Record Added against ID $id", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, BarangActivity::class.java))
        return super.onSupportNavigateUp()
    }

}
