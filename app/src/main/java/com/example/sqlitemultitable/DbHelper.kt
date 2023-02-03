package com.example.sqlitemultitable

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.sqlitemultitable.model.ReadAllDataModel
import com.example.sqlitemultitable.model.SalaryMode
import com.example.sqlitemultitable.model.TableOneModel

class DbHelper(var activity: Activity) : SQLiteOpenHelper(activity, "Chintan_Db", null, 1) {

    private lateinit var database: SQLiteDatabase
    private lateinit var list: java.util.ArrayList<SalaryMode>
    private lateinit var readList: java.util.ArrayList<TableOneModel>
    val TAG = "DBHELPER"


    override fun onCreate(db: SQLiteDatabase?) {

        database = db!!
        val query =
            " CREATE TABLE table1(ID INTEGER PRIMARY KEY AUTOINCREMENT, name Text,date Text,designation Text,qualification Text,gender Text,country Text,state Text,city Text,email Text,isRelocate Text,mobile Text)"
        db!!.execSQL(query)

        val salaryTable = " CREATE TABLE salarytable(SID INTEGER PRIMARY KEY AUTOINCREMENT, tableid Text,expectedsalary Text,companeysalary Text)"
//        val salaryTable = " CREATE TABLE salarytable(SID INTEGER PRIMARY KEY AUTOINCREMENT, tableid Text,expectedsalary Text,companeysalary Text, FOREIGN KEY(\"+tableid+\") REFERENCES \"+DatabaseManagerContact.table1+\"(\"+ID+\"))"
        db.execSQL(salaryTable)
        //Log.e(TAG, "onCreate: SalaryTab=="+db!!.execSQL(salaryTable))
     }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(
        Name: String,
        Date: String,
        Designation: String,
        Qualification: String,
        Gender: String,
        City: String,
        State: String,
        Country: String,
        Email: String,
        IsRelocate: Boolean,
        MobileNumber: String
    ) {

        var db = writableDatabase
        val containtValue = ContentValues()
        containtValue.put("name", Name)
        containtValue.put("date", Date)
        containtValue.put("designation", Designation)
        containtValue.put("qualification", Qualification)
        containtValue.put("gender", Gender)
        containtValue.put("country", Country)
        containtValue.put("state", State)
        containtValue.put("city", City)
        containtValue.put("email", Email)
        containtValue.put("isRelocate", IsRelocate)
        containtValue.put("mobile", MobileNumber)

        val value = db.insert("table1", null, containtValue)
        Toast.makeText(activity, "insert:" + value, Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("Range")
    fun readData(): ArrayList<TableOneModel> {

        var db: SQLiteDatabase = writableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM table1", null)
        readList = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("ID"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val designation = cursor.getString(cursor.getColumnIndex("designation"))
                val qualification = cursor.getString(cursor.getColumnIndex("qualification"))
                val gender = cursor.getString(cursor.getColumnIndex("gender"))
                val country = cursor.getString(cursor.getColumnIndex("country"))
                val state = cursor.getString(cursor.getColumnIndex("state"))
                val city = cursor.getString(cursor.getColumnIndex("city"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val isRelocate = cursor.getString(cursor.getColumnIndex("isRelocate"))
                val mobile = cursor.getString(cursor.getColumnIndex("mobile"))

                //Log.e(TAG, "ReadData: =="+id+" :: "+name+" :: "+date+" :: "+designation+" :: "+qualification+ "" + " :: "+gender+" :: "+country+" :: "+state+" :: "+city+" :: "+email+" :: "+isRelocate+" :: "+mobile )

                var onemodel = TableOneModel(id,name, date, designation, qualification, gender, country, state, city, email, isRelocate, mobile)
                readList.add(onemodel)
            } while (cursor.moveToNext())
        }
        db.close()
        return readList;

    }

    fun deleteReco(id: String) {

        val db = this.writableDatabase
        db.delete("table1", "ID=$id",null)
        db.close()


    }

    fun EditData(
        id: String,
        mName: String,
        mDate: String,
        mDesignation: String,
        mQualification: String,
        mGender: String,
        mCity: String,
        mState: String,
        mCountry: String,
        mEmail: String,
        mIsRelocate: Boolean,
        mMobileNumber: String
    ) {

        var db = writableDatabase
        val containtValue1 = ContentValues()
        containtValue1.put("name", mName)
        containtValue1.put("date", mDate)
        containtValue1.put("designation", mDesignation)
        containtValue1.put("qualification", mQualification)
        containtValue1.put("gender", mGender)
        containtValue1.put("country", mCountry)
        containtValue1.put("state", mState)
        containtValue1.put("city", mCity)
        containtValue1.put("email", mEmail)
        containtValue1.put("isRelocate", mIsRelocate)
        containtValue1.put("mobile", mMobileNumber)

        Log.e(TAG, ":For Edit ==== "+ id +" :: "+mName+" :: "+mDate+" :: "+mDesignation+" :: "+mQualification+" :: "+mGender+ "" + " :: "+mCity+" :: "+mState+" :: "+mCountry+" :: "+mEmail+" :: "+mIsRelocate+" :: "+mMobileNumber )

        val update = db.update("table1",containtValue1, "ID=$id", null);

        Toast.makeText(activity,"update : "+update,Toast.LENGTH_SHORT).show();
        db.close();

    }

    fun insertSalary(userId: String, sExpactedSalary: String, sCompaneyProvideSalary: String) {

        var db = writableDatabase
        val containtValue = ContentValues()

        containtValue.put("tableid",userId)
        containtValue.put("expectedsalary",sExpactedSalary)
        containtValue.put("companeysalary",sCompaneyProvideSalary)

        Log.e(TAG, "insertSalary: SalaryDataInsert-"+userId+" :: "+sExpactedSalary+" + :: + "+sCompaneyProvideSalary)

        val value = db.insert("salarytable", null, containtValue)
        Toast.makeText(activity, "insert:" + value, Toast.LENGTH_SHORT).show();

        //db.close();

    }

    @SuppressLint("Range")
    fun readSalaryData(userId: String): SalaryMode {
        list = ArrayList<SalaryMode>();
        lateinit var salaryMode: SalaryMode



        val reasSalaryData = "SELECT * FROM salarytable WHERE tableid = $userId"
        val db = writableDatabase
        val cursor = db.rawQuery(reasSalaryData, null)

        if (cursor.count > 0){

            if (cursor.moveToFirst()) {
                do {
                    val salId = cursor.getString(cursor.getColumnIndex("SID"))
                    val userId = cursor.getString(cursor.getColumnIndex("tableid"))
                    val expactedSal = cursor.getString(cursor.getColumnIndex("expectedsalary"))
                    val cSalary = cursor.getString(cursor.getColumnIndex("companeysalary"))

                    salaryMode = SalaryMode(salId, userId, expactedSal, cSalary);
                    //list.add(salaryMode)

                } while (cursor.moveToNext())
            }
            return salaryMode

        }else{
            return SalaryMode("","","","")
        }


    }

    fun updateSalaryData(
        salaryTabId: String,
        userId: String,
        sExpactedSalary: String,
        sCompaneyProvideSalary: String
    ) {

        val db = writableDatabase;
        val containtValue = ContentValues()
        containtValue.put("tableid",userId)
        containtValue.put("expectedsalary",sExpactedSalary)
        containtValue.put("companeysalary",sCompaneyProvideSalary)

        val update = db.update("salarytable",containtValue,"SID=$salaryTabId",null)
        Log.e(TAG, "updateSalaryData: SalaryUpdate"+ update)

    }

    @SuppressLint("Range")
    fun readAllData(): ArrayList<ReadAllDataModel> {
        val allRecordList = ArrayList<ReadAllDataModel>()
        lateinit var readAllData: ReadAllDataModel
        val db = writableDatabase
        val cursor = db.rawQuery(
            "SELECT table1.name,table1.date, table1.designation,table1.qualification,table1.gender,table1.country,table1.state,table1.city,table1.email,table1.isRelocate,table1.mobile,salarytable.expectedsalary,salarytable.companeysalary " +
                    "FROM table1,salarytable WHERE table1.ID = salarytable.tableid",
            null
        )

        if (cursor.count > 0) {

            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("table1.name"))
                    val date = cursor.getString(cursor.getColumnIndex("table1.date"))
                    val designation = cursor.getString(cursor.getColumnIndex("table1.designation"))
                    val qualification = cursor.getString(cursor.getColumnIndex("table1.qualification"))
                    val gender = cursor.getString(cursor.getColumnIndex("table1.gender"))
                    val country = cursor.getString(cursor.getColumnIndex("table1.country"))
                    val state = cursor.getString(cursor.getColumnIndex("table1.state"))
                    val city = cursor.getString(cursor.getColumnIndex("table1.city"))
                    val email = cursor.getString(cursor.getColumnIndex("table1.email"))
                    val isRelocate = cursor.getString(cursor.getColumnIndex("table1.isRelocate"))
                    val mobile = cursor.getString(cursor.getColumnIndex("table1.mobile"))
                    val expectedSalary = cursor.getString(cursor.getColumnIndex("salarytable.expectedsalary"))
                    val companeysalary = cursor.getString(cursor.getColumnIndex("salarytable.companeysalary"))

                    Log.e(TAG, "  Data -----= "+name+" :: "+designation+" :: "+qualification+" :: "+expectedSalary+" :: "+companeysalary )
                    readAllData = ReadAllDataModel(name, date,designation, qualification,gender,country,state,city,email,isRelocate,mobile, expectedSalary,companeysalary);
                    allRecordList.add(readAllData)

                } while (cursor.moveToNext())
            }
            return allRecordList

        } else {
            return allRecordList
        }


    }


}