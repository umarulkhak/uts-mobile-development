package com.example.tokobunda.Dashnoard.Barang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.example.tokobunda.Dashnoard.MainActivity
import com.example.tokobunda.Model.AdapterRecordBarang
import com.example.tokobunda.Model.Constants
import com.example.tokobunda.Model.DbHelper
import com.example.tokobunda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BarangActivity : AppCompatActivity() {
    //actionbar
    private var actionBar: ActionBar?=null

    //db helper
    lateinit var dbHelper: DbHelper

    //orderby/so queries
    private val NEWEST_FIRST = "${Constants.ADDED_TIMESTAMP} DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)
        //setting up actionbar
        actionBar = supportActionBar
        actionBar!!.title = "Daftar Barang"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //init db helper
        dbHelper = DbHelper(this)
        loadRecords()
        //click FloatingActionbutton to start AddUpdateRecordActivity
        findViewById<FloatingActionButton>(R.id.addRecordBtnp).setOnClickListener {
            startActivity(Intent(this, AddUpdateRecordBarangActivity::class.java))
        }
    }

    private fun loadRecords() {
        val adapterRecord = AdapterRecordBarang(this, dbHelper.getAllRecordsBarang(NEWEST_FIRST))
        findViewById<RecyclerView>(R.id.recordsBarangRvp).adapter = adapterRecord
    }


    public  override fun onResume() {
        super.onResume()
        loadRecords()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this,MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}