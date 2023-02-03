package com.example.sqlitemultitable

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitemultitable.model.TableOneModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var positionId: String
    private lateinit var dbHelper: DbHelper
    private lateinit var mEmail: String
    private lateinit var mMobileNumber: String
    private var mIsRelocate: Boolean = false
    private lateinit var mCountry: String
    private lateinit var mState: String
    private lateinit var mCity: String
    private lateinit var selectedCountry: String
    private lateinit var selectedState: String
    private lateinit var selectedCity: String
    private lateinit var mGender: String
    private var genderValue: String = "null"
    private lateinit var mQualification: String
    private lateinit var mDesignation: String
    private lateinit var mDate: String
    private lateinit var mName:String
    var TAG  : String = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        positionId = intent.getStringExtra("positionId").toString()
        //positionId = intent.getStringExtra("forEdit").toString()
        Log.e(TAG, "onCreate: Position TO EditData+++ "+positionId )
        clickEvents()

        dbHelper = DbHelper(this@MainActivity)

        if (intent.getBooleanExtra("forEdit",false)){
            setvalueInSpinner()
            val gson = Gson()
            val data: TableOneModel? = gson.fromJson(intent.getStringExtra("myjson"), TableOneModel::class.java)
            Log.e(TAG, "onCreate: Dataa==============++++"+data )

            Toast.makeText(this@MainActivity,"welcome to edit",Toast.LENGTH_SHORT).show()
            txtAddExpectedSalary.visibility = VISIBLE
            txtAddExpectedSalary.setOnClickListener {

                //mESalary.visibility = VISIBLE
                val intent = Intent(this@MainActivity,AddSalaryActivity::class.java)
                intent.putExtra("userId", data!!.id)
                startActivity(intent)

            }

            name.editText!!.setText(data!!.name.toString())
            date.editText!!.setText(data!!.date.toString())
            designation.editText!!.setText(data!!.designation.toString())
            qualification.editText!!.setText(data!!.qualification.toString())

            if (data.gender.equals("  Male")){
                radioMale.isChecked =true
                radioFemale.isChecked = false
            }else{
                radioMale.isChecked =false
                radioFemale.isChecked = true
            }

            selectedCity = data.city
            selectedCountry = data.country
            selectedState = data.state


            //country.setSelected(data.country)

            email.editText!!.setText(data!!.email.toString())
            if (data.isRelocate.equals("1")){
                isRelocate.isChecked = true
            }else{
                isRelocate.isChecked = false
            }

            mobileNumber.editText!!.setText(data!!.mobile.toString())


            btn_save.setOnClickListener {

                mName = name.editText!!.text.toString()
                mDate = date.editText!!.text.toString()
                mDesignation = designation.editText!!.text.toString()
                mQualification = qualification.editText!!.text.toString()
                mGender = genderValue
                mCity = selectedCity
                mState = selectedState
                mCountry = selectedCountry
                mEmail = email.editText!!.text.toString()
                mIsRelocate = isRelocate.isChecked
                mMobileNumber = mobileNumber.editText!!.text.toString()

                Log.e(TAG, "clickEvents: =="+mName+" :: "+mDate+" :: "+mDesignation+" :: "+mQualification+" :: "+mGender+ "" + " :: "+mCity+" :: "+mState+" :: "+mCountry+" :: "+mEmail+" :: "+mIsRelocate+" :: "+mMobileNumber )
                dbHelper.EditData(data!!.id,mName,mDate,mDesignation,mQualification,mGender,mCity,mState,mCountry,mEmail,mIsRelocate,mMobileNumber)

                finish()
                //startActivity(Intent(this@MainActivity,ReadDataActivity::class.java))
            }


        }else{

            setvalueInSpinner()
            dbHelper = DbHelper(this@MainActivity)

        }

        val rg = radioGroup as RadioGroup
        rg.setOnCheckedChangeListener { group, checkedId ->
            genderValue =
                (findViewById<View>(rg.checkedRadioButtonId) as RadioButton).text.toString()
            //Toast.makeText(baseContext, genderValue, Toast.LENGTH_SHORT).show()
        }


    }

    private fun setvalueInSpinner() {

        val countryName = resources.getStringArray(R.array.countries_array)
        val stateName = resources.getStringArray(R.array.india_states)
        val cityName = resources.getStringArray(R.array.cityName)

        if (country != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, countryName
            )
            country.adapter = adapter

        }

        if (state != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, stateName
            )
            state.adapter = adapter

        }

        if (city != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, cityName
            )
            city.adapter = adapter

        }

        country.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedCountry = countryName[position]
                //Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + countryName[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        state.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedState = stateName[position]
                //Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + stateName[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        city.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

               // Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + cityName[position], Toast.LENGTH_SHORT).show()
                selectedCity = cityName[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun clickEvents() {

        date.editText!!.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                //Toast.makeText(applicationContext, "Got the focus", Toast.LENGTH_LONG).show()
                ShoeDatePickerDialog()
            } else {
               // Toast.makeText(applicationContext, "Lost the focus", Toast.LENGTH_LONG).show()
            }
        }

        btn_save.setOnClickListener {

            mName = name.editText!!.text.toString()
            mDate = date.editText!!.text.toString()
            mDesignation = designation.editText!!.text.toString()
            mQualification = qualification.editText!!.text.toString()
            mGender = genderValue
            mCity = selectedCity
            mState = selectedState
            mCountry = selectedCountry
            mEmail = email.editText!!.text.toString()

            mIsRelocate = isRelocate.isChecked

            mMobileNumber = mobileNumber.editText!!.text.toString()

            Log.e(TAG, "clickEvents: =="+mName+" :: "+mDate+" :: "+mDesignation+" :: "+mQualification+" :: "+mGender+ "" + " :: "+mCity+" :: "+mState+" :: "+mCountry+" :: "+mEmail+" :: "+mIsRelocate+" :: "+mMobileNumber )
            dbHelper.insertData(mName,mDate,mDesignation,mQualification,mGender,mCity,mState,mCountry,mEmail,mIsRelocate,mMobileNumber)

            finish()
        }
        btn_cancel.setOnClickListener {
        }

    }

    @SuppressLint("SetTextI18n")
    private fun ShoeDatePickerDialog() {
        var  newCalendar = Calendar.getInstance()
        val StartTime = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                date.editText!!.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
            },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        StartTime.show()
    }

}


