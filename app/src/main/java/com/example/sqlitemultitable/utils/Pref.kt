package com.example.sqlitemultitable.utils

import android.content.Context
import android.content.SharedPreferences

object Pref {

    var application = Application()

    private fun get(): SharedPreferences {
        return application.getAppContext().getSharedPreferences(Constants.APP_Name_PREF, Context.MODE_PRIVATE)
    }

    fun removePref(): Boolean {
        return get().edit().clear().commit()
    }

    fun setString(_key: String?, value: String?): Boolean {
        return get().edit().putString(_key, value).commit()
    }

    fun setDouble(_key: String?, value: Float?): Boolean {
        return get().edit().putFloat(_key, value!!).commit()
    }

    fun getDouble(_key: String?): Float {
        return get().getFloat(_key, 0.0f)
    }

    fun getString(_key: String?): String? {
        return get().getString(_key, "")
    }

    fun getString(_key: String?, def_value: String?): String? {
        return get().getString(_key, def_value)
    }

    fun getInt(_key: String?): Int {
        return get().getInt(_key, 0)
    }

    fun setInt(_key: String?, value: Int): Boolean {
        return get().edit().putInt(_key, value).commit()
    }

    fun getBoolean(_key: String?): Boolean {
        return get().getBoolean(_key, false)
    }

    fun setBoolean(_key: String?, value: Boolean): Boolean {
        return get().edit().putBoolean(_key, value).commit()
    }

    fun remove(_key: String?) {
        get().edit().remove(_key).commit()
    }

    fun isContainKey(_key: String?): Boolean {
        return get().contains(_key)
    }


}