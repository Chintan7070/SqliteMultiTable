package com.example.sqlitemultitable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class EditRecord : AppCompatActivity() {
    private lateinit var positionId: String
    val TAG = "EditActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)

         positionId = intent.getStringExtra("positionId").toString()
        Log.e(TAG, "onCreate: Position TO EditData+++ "+positionId )
    }
}