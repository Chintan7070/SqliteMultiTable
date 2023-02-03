package com.example.sqlitemultitable

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqlitemultitable.utils.BaseActivity
import com.example.sqlitemultitable.utils.Constants
import com.example.sqlitemultitable.utils.Pref
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_select_language.*
import kotlinx.android.synthetic.main.view_language_list.*
import java.util.*

class SelectLanguageActivity : BaseActivity() {
    lateinit var pref : Pref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)
        pref = Pref

        moveNext.setOnClickListener {
            startActivity(Intent(this@SelectLanguageActivity,ReadDataActivity::class.java))
        }

        btnEnglish.setOnClickListener{ updateLocale(Locales.English)
            pref.setString(Constants.SelectLanguage,"English")
        }
        btnHindi.setOnClickListener{ updateLocale(Locales.Hindi) }
        btnTurkis.setOnClickListener{ updateLocale(Locales.Turkish) }
        btnUrdu.setOnClickListener{ updateLocale(Locales.Urdu) }
        btnArbic.setOnClickListener{ updateLocale(Locales.Arabic) }
        btnChin.setOnClickListener{ updateLocale(Locales.China) }

    }

    override fun updateLocale(locale: Locale) {
        super.updateLocale(locale)
        setTitle(R.string.English)
    }
}