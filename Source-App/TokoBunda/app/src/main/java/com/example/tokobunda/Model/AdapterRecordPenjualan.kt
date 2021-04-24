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
import com.example.tokobunda.Dashnoard.Penjualan.AddUpdateRecordPenjualanActivity
import com.example.tokobunda.Dashnoard.Penjualan.PenjualanActivity
import com.example.tokobunda.R
import java.util.ArrayList

class AdapterRecordPenjualan(): RecyclerView.Adapter<AdapterRecordPenjualan.HolderRecord>(){
    private var context: Context?=null
    private var recordList: ArrayList<ModelRecordPenjualan>?=null

    lateinit var dbHelper:DbHelper

    constructor(context: Context?, recordList: ArrayList<ModelRecordPenjualan>?) : this() {
        this.context = context
        this.recordList = recordList

        dbHelper = DbHelper(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        //inflate the layout row_record.xml
        return HolderRecord(
            LayoutInflater.from(context).inflate(R.layout.row_record_terjual, parent, false)
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
        val addedTime = model.addedTime
        val updatedTime = model.updatedTime

        //set data views
        holder.namaTvp.text = nama
        holder.jumlahTvp.text = jumlah


        //handle more button click: show edit/delete options
        holder.moreBtnp.setOnClickListener {
            showMoreOptionPerson(
                position,
                id,
                nama,
                jumlah,
                addedTime,
                updatedTime

            )
        }
    }

    private fun showMoreOptionPerson(position: Int,
                                     id: String,
                                     nama: String,
                                     jumlah: String,
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
                val intent = Intent(context, AddUpdateRecordPenjualanActivity::class.java)
                intent.putExtra("C_ID", id)
                intent.putExtra("C_NAMA_TERJUAL", nama)
                intent.putExtra("C_JUMLAH_TERJUAL", jumlah)
                intent.putExtra("C_ADDED_TIME", addedTime)
                intent.putExtra("isEditMode", true)
                context!!.startActivity(intent)

            } else{
                //delete clicked
                dbHelper.deleteRecordPenjualan(id)
                //refresh record by calling activity's onResume method
                (context as PenjualanActivity)!!.onResume();
            }

        }
        //show dialog
        dialog.show()
    }

    inner class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView) {
        //view from row_record.xml
        var namaTvp: TextView = itemView.findViewById(R.id.namaterjualTvp)
        var jumlahTvp: TextView = itemView.findViewById(R.id.jumlahterjualTvp)
        var moreBtnp: ImageButton = itemView.findViewById(R.id.moreBtnpTerjual)

    }

}