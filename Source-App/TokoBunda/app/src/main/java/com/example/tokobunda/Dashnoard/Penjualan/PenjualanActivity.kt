package com.example.tokobunda.Dashnoard.Penjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.example.tokobunda.Dashnoard.MainActivity
import com.example.tokobunda.Model.AdapterRecordPenjualan
import com.example.tokobunda.Model.Constants
import com.example.tokobunda.Model.DbHelper
import com.example.tokobunda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PenjualanActivity : AppCompatActivity() {
    //actionbar
    private var actionBar: ActionBar?=null

    //db helper
    lateinit var dbHelper: DbHelper

    //orderby/so queries
    private val NEWEST_FIRST = "${Constants.C_ADDED_TIMESTAMP} DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjualan)
        //setting up actionbar
        actionBar = supportActionBar
        actionBar!!.title = "Daftar Penjualan"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //init db helper
        dbHelper = DbHelper(this)
        loadRecords()
        //click FloatingActionbutton to start AddUpdateRecordActivity
        findViewById<FloatingActionButton>(R.id.addRecordBtnpterjual).setOnClickListener {
            startActivity(Intent(this, AddUpdateRecordPenjualanActivity::class.java))
        }
    }

    private fun loadRecords() {
        val adapterRecord = AdapterRecordPenjualan(this, dbHelper.getAllRecordsPenjualan(NEWEST_FIRST))
        findViewById<RecyclerView>(R.id.recordsterjualRvp).adapter = adapterRecord
    }


    public  override fun onResume() {
        super.onResume()
        loadRecords()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}