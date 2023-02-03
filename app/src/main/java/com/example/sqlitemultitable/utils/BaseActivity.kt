package com.example.sqlitemultitable.utils

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.example.sqlitemultitable.R
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity

abstract class BaseActivity : LocaleAwareCompatActivity() {

    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = Pref
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dark -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                pref.setBoolean(Constants.isNightMode,true)

            }
            R.id.light -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                pref.setBoolean(Constants.isNightMode,false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}