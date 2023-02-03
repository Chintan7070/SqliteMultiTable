package com.example.sqlitemultitable

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.sqlitemultitable.adapter.ReadDataAdapter
import com.example.sqlitemultitable.model.ReadAllDataModel
import com.example.sqlitemultitable.model.TableOneModel
import com.example.sqlitemultitable.utils.ItemClickInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_read_data.*


class ReadDataActivity : AppCompatActivity() {
    private lateinit var allRecordList: java.util.ArrayList<ReadAllDataModel>
    private lateinit var readDataAdapter: ReadDataAdapter
    private lateinit var readList: ArrayList<TableOneModel>
    private lateinit var dbHelper: DbHelper
    val TAG = "ReadDataActivty"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)

        readData();
        imgAddEmpl.setOnClickListener {
            startActivity(Intent(this@ReadDataActivity, MainActivity::class.java))
        }

    }

    private fun readData() {
        dbHelper = DbHelper(this@ReadDataActivity)
        readList = ArrayList()
        readList = dbHelper.readData()
        Log.e(TAG, "onCreate: ReadData :::" + readList)
        setdataInAdapter(readList);


    }

    private fun setdataInAdapter(readList: ArrayList<TableOneModel>) {

        readDataAdapter = ReadDataAdapter(this@ReadDataActivity, readList, object : ItemClickInterface {
                override fun clickEvent(position: Int) {
                    showData(position, readList);
                }

            override fun clickEventLong(position: Int) {
                readAllDataAndShow(position)
            }
        })
        var lm = LinearLayoutManager(this@ReadDataActivity, VERTICAL, false)
        rv_View.layoutManager = lm
        rv_View.adapter = readDataAdapter;
    }

    private fun readAllDataAndShow(position: Int) {

        allRecordList = dbHelper.readAllData();
        //Log.e(TAG, "readAllDataAndShow: AllRecord:="+allRecordList )
        try {
            if (allRecordList[position].expactedSal.isEmpty()){
                Toast.makeText(this,"Add salary Detail",Toast.LENGTH_LONG).show()
            }else{
                showAllData(allRecordList,position)
            }
        }catch (e : java.lang.Exception){
            Toast.makeText(this@ReadDataActivity,"enter salary detail",Toast.LENGTH_LONG).show()
        }

    }

    private fun showAllData(allRecordList: java.util.ArrayList<ReadAllDataModel>, position: Int) {

        val dialog = Dialog(this@ReadDataActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.newcustom_layout)
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));


        val buttonShoworNot = dialog.findViewById<LinearLayout>(R.id.buttonShoworNot)
        buttonShoworNot.visibility = View.GONE
        val rImage = dialog.findViewById<ImageView>(R.id.rImage)
        val btnDelete = dialog.findViewById<TextView>(R.id.tvDelete)
        val brnEdit = dialog.findViewById<TextView>(R.id.tvEdit)
        val trName = dialog.findViewById<TextView>(R.id.trName)
        val trEmail = dialog.findViewById<TextView>(R.id.trEmail)
        val rEmail = dialog.findViewById<TextView>(R.id.rEmail)
        val trDesig = dialog.findViewById<TextView>(R.id.trDesig)
        val rName = dialog.findViewById<TextView>(R.id.rName)
        val rDate = dialog.findViewById<TextView>(R.id.rDate)
        val rDesig = dialog.findViewById<TextView>(R.id.rDesig)
        val rQualif = dialog.findViewById<TextView>(R.id.rQualif)
        val rGender = dialog.findViewById<TextView>(R.id.rGender)
        val rCountry = dialog.findViewById<TextView>(R.id.rCountry)
        val rCity = dialog.findViewById<TextView>(R.id.rCity)
        val rState = dialog.findViewById<TextView>(R.id.rState)
        val rRelocate = dialog.findViewById<TextView>(R.id.rRelocate)
        val rMobileNo = dialog.findViewById<TextView>(R.id.rMobileNo)
        val salaryDataShow = dialog.findViewById<LinearLayout>(R.id.salaryDataShow)
        val expectedSalary = dialog.findViewById<TextView>(R.id.expectedSalary)
        val companeyPSalary = dialog.findViewById<TextView>(R.id.companeyPSalary)

        salaryDataShow.visibility = View.VISIBLE

        if (allRecordList[position].gender.equals("  Male") || allRecordList[position].gender.equals("  male")) {
            rImage.setImageResource(R.drawable.ic_man)
            Log.e(TAG, "showData: gender::" + allRecordList[position].gender)
        } else {
            rImage.setImageResource(R.drawable.ic_woman)
            Log.e(TAG, "showData: gender::" + allRecordList[position].gender)

        }

        trName.text = allRecordList[position].name.toString();
        trEmail.text = allRecordList[position].email.toString();
        trDesig.text = allRecordList[position].designation.toString();
        rName.text = allRecordList[position].name.toString();
        rDate.text = allRecordList[position].date
        rDesig.text = allRecordList[position].designation
        rQualif.text = allRecordList[position].qualification
        rGender.text = allRecordList[position].gender
        rCountry.text = allRecordList[position].country
        rCity.text = allRecordList[position].city
        rState.text = allRecordList[position].state
        rEmail.text = allRecordList[position].email
        rRelocate.text = allRecordList[position].isRelocate
        rMobileNo.text = allRecordList[position].mobile
        expectedSalary.text = allRecordList[position].expactedSal
        companeyPSalary.text = allRecordList[position].cSalary


        /*btnDelete.setOnClickListener {
            val dbHelper = DbHelper(this@ReadDataActivity)
            dbHelper.deleteReco(rreadAllData.id);
            dialog.dismiss();
            readDataAdapter.notifyDataSetChanged()
            readData()
        }
        brnEdit.setOnClickListener {

            var tableOneModel: TableOneModel = readList[position]
            val gson = Gson()
            val myJson = gson.toJson(tableOneModel)

            var intent = Intent(this@ReadDataActivity, MainActivity::class.java)
            intent.putExtra("forEdit", true)
            intent.putExtra("positionId", readList[position].id)
            intent.putExtra("myjson", myJson)
            startActivity(intent)
            dialog.dismiss()
        }*/
        dialog.show()


    }

    private fun showData(position: Int, readList: ArrayList<TableOneModel>) {


        val dialog = Dialog(this@ReadDataActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.newcustom_layout)
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));


        val rImage = dialog.findViewById<ImageView>(R.id.rImage)
        val btnDelete = dialog.findViewById<TextView>(R.id.tvDelete)
        val brnEdit = dialog.findViewById<TextView>(R.id.tvEdit)
        val trName = dialog.findViewById<TextView>(R.id.trName)
        val trEmail = dialog.findViewById<TextView>(R.id.trEmail)
        val rEmail = dialog.findViewById<TextView>(R.id.rEmail)
        val trDesig = dialog.findViewById<TextView>(R.id.trDesig)
        val rName = dialog.findViewById<TextView>(R.id.rName)
        val rDate = dialog.findViewById<TextView>(R.id.rDate)
        val rDesig = dialog.findViewById<TextView>(R.id.rDesig)
        val rQualif = dialog.findViewById<TextView>(R.id.rQualif)
        val rGender = dialog.findViewById<TextView>(R.id.rGender)
        val rCountry = dialog.findViewById<TextView>(R.id.rCountry)
        val rCity = dialog.findViewById<TextView>(R.id.rCity)
        val rState = dialog.findViewById<TextView>(R.id.rState)
        val rRelocate = dialog.findViewById<TextView>(R.id.rRelocate)
        val rMobileNo = dialog.findViewById<TextView>(R.id.rMobileNo)

        if (readList[position].gender.equals("  Male") || readList[position].gender.equals("  male")) {
            rImage.setImageResource(R.drawable.ic_man)
            Log.e(TAG, "showData: gender::" + readList[position].gender)
        } else {
            rImage.setImageResource(R.drawable.ic_woman)
            Log.e(TAG, "showData: gender::" + readList[position].gender)

        }

        trName.text = readList[position].name.toString();
        trEmail.text = readList[position].email.toString();
        trDesig.text = readList[position].designation.toString();
        rName.text = readList[position].name.toString();
        rDate.text = readList[position].date
        rDesig.text = readList[position].designation
        rQualif.text = readList[position].qualification
        rGender.text = readList[position].gender
        rCountry.text = readList[position].country
        rCity.text = readList[position].city
        rState.text = readList[position].state
        rEmail.text = readList[position].email
        rRelocate.text = readList[position].isRelocate
        rMobileNo.text = readList[position].mobile


        btnDelete.setOnClickListener {
            val dbHelper = DbHelper(this@ReadDataActivity)
            dbHelper.deleteReco(readList[position].id);
            dialog.dismiss();
            readDataAdapter.notifyDataSetChanged()
            readData()
        }
        brnEdit.setOnClickListener {

            var tableOneModel: TableOneModel = readList[position]
            val gson = Gson()
            val myJson = gson.toJson(tableOneModel)

            var intent = Intent(this@ReadDataActivity, MainActivity::class.java)
            intent.putExtra("forEdit", true)
            intent.putExtra("positionId", readList[position].id)
            intent.putExtra("myjson", myJson)
            startActivity(intent)
            dialog.dismiss()
        }
        dialog.show()

    }

    override fun onResume() {
        super.onResume()
        readData();
    }
}