package com.example.sqlitemultitable.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.example.sqlitemultitable.SelectLanguageActivity
import com.zeugmasolutions.localehelper.LocaleAwareApplication

class Application : LocaleAwareApplication(), Application.ActivityLifecycleCallbacks {

    private lateinit var pref: Pref
    companion object {
        private lateinit var context: Context
    }    val TAG = "Application"

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        registerActivityLifecycleCallbacks(this@Application)

        Log.e(TAG, "onCreate: Activity :== "+ context )
        pref = Pref
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
         if (pref.getBoolean(Constants.isNightMode)){
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
             Log.e(TAG, "onCreate: isNightMode")

         }else{
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
             Log.e(TAG, "onCreate: isNotNightMode")

         }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun getAppContext(): Context {
        return context
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.e(TAG, "onActivityCreated: OnactivityCreat000000000000")
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity is SelectLanguageActivity) {
            Log.e(TAG, "onActivityStarted:--- "+activity )
        }else{
            Log.e(TAG, "onActivityStarted:--- "+activity )
        }
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}