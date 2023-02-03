package com.example.sqlitemultitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlitemultitable.model.SalaryMode
import kotlinx.android.synthetic.main.activity_add_salary.*
import java.util.ArrayList

class AddSalaryActivity : AppCompatActivity() {
    private lateinit var salaryTabId: String
    private lateinit var salarydata: SalaryMode
    private lateinit var userId: String
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_salary)

        userId = intent.getStringExtra("userId").toString()
        dbHelper = DbHelper(this@AddSalaryActivity)
        clickEvents();
        gettheSalaryDetail(userId)


    }

    private fun gettheSalaryDetail(userId: String) {

        salarydata = dbHelper.readSalaryData(userId)
        ExpectedSalary.editText?.setText(salarydata.expactedSal)
        companeyProvideSalary.editText?.setText(salarydata.cSalary)
        salaryTabId = salarydata.salId
    }

    private fun clickEvents() {
        addSalary.setOnClickListener {

            val sExpactedSalary = ExpectedSalary.editText!!.text.toString()
            val sCompaneyProvideSalary = companeyProvideSalary.editText!!.text.toString()

            if (salarydata.expactedSal != "" && salarydata.cSalary!=""){
                dbHelper.updateSalaryData(salaryTabId,userId, sExpactedSalary, sCompaneyProvideSalary)
                finish();
                Toast.makeText(this@AddSalaryActivity,"Salary Update",Toast.LENGTH_SHORT).show()

            }else{

                if (sExpactedSalary != "" && sCompaneyProvideSalary!= "") {
                    Toast.makeText(this@AddSalaryActivity," Insert",Toast.LENGTH_SHORT).show()
                    dbHelper.insertSalary(userId, sExpactedSalary, sCompaneyProvideSalary)
                    finish();
                }else{
                    Toast.makeText(this@AddSalaryActivity,"Please enter all data",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}