package com.example.tokobunda.Model

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokobunda.Dashnoard.Barang.AddUpdateRecordBarangActivity
import com.example.tokobunda.Dashnoard.Barang.BarangActivity
import com.example.tokobunda.R
import java.util.ArrayList

class AdapterRecordBarang(): RecyclerView.Adapter<AdapterRecordBarang.HolderRecord>(){
    private var context: Context?=null
    private var recordList: ArrayList<ModelRecordBarang>?=null

    lateinit var dbHelper:DbHelper

    constructor(context: Context?, recordList: ArrayList<ModelRecordBarang>?) : this() {
        this.context = context
        this.recordList = recordList

        dbHelper = DbHelper(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        //inflate the layout row_record.xml
        return HolderRecord(
            LayoutInflater.from(context).inflate(R.layout.row_record_barang, parent, false)
        )
    }

    override fun getItemCount(): Int {
        //return items/record/list size
        return recordList!!.size
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        //get data, set data, handle clicks

        //get data
        val model = recordList!!.get(position)

        val id = model.id
        val nama = model.nama
        val jumlah = model.jumlah
        val harga = model.harga
        val addedTime = model.addedTime
        val updatedTime = model.updatedTime

        //set data views
        holder.namaTvp.text = nama
        holder.jumlahTvp.text = jumlah
        holder.hargaTvp.text = harga


        //handle more button click: show edit/delete options
        holder.moreBtnp.setOnClickListener {
            showMoreOptionPerson(
                position,
                id,
                nama,
                jumlah,
                harga,
                addedTime,
                updatedTime

            )
        }
    }

    private fun showMoreOptionPerson(position: Int,
                                     id: String,
                                     nama: String,
                                     jumlah: String,
                                     harga: String,
                                     addedTime: String,
                                     updatedTime: String) {
        //options to display
        val options = arrayOf("Edit", "Delete")
        //dialog
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        //set items and click listener
        dialog.setItems(options){dialog, which ->
            //handle item clicks
            if (which==0){
                //Edit clicked
                val intent = Intent(context, AddUpdateRecordBarangActivity::class.java)
                intent.putExtra("ID", id)
                intent.putExtra("NAMA_BARANG", nama)
                intent.putExtra("JUMLAH_BARANG", jumlah)
                intent.putExtra("HARGA", harga)
                intent.putExtra("ADDED_TIME", addedTime)
                intent.putExtra("isEditMode", true)
                context!!.startActivity(intent)

            } else{
                //delete clicked
                dbHelper.deleteRecordBarang(id)
                //refresh record by calling activity's onResume method
                (context as BarangActivity)!!.onResume();
            }

        }
        //show dialog
        dialog.show()
    }

    inner class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView) {
        //view from row_record.xml
        var namaTvp: TextView = itemView.findViewById(R.id.namaTvp)
        var jumlahTvp: TextView = itemView.findViewById(R.id.jumlahTvp)
        var hargaTvp: TextView = itemView.findViewById(R.id.hargaTvp)
        var moreBtnp: ImageButton = itemView.findViewById(R.id.moreBtnp)

    }

}