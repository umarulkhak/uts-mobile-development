package com.example.tokobunda.Dashnoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tokobunda.Dashnoard.Barang.BarangActivity
import com.example.tokobunda.Dashnoard.Penjualan.PenjualanActivity
import com.example.tokobunda.Login.Login
import com.example.tokobunda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val logout = findViewById<FloatingActionButton>(R.id.fb_logout)
        logout.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
        cv_barang.setOnClickListener {
            startActivity(Intent(this, BarangActivity::class.java))
            finish()
        }
        cv_penjualan.setOnClickListener {
            startActivity(Intent(this, PenjualanActivity::class.java))
            finish()
        }
    }
}