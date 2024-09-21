package com.app.ecom.utils

import android.content.Context
import android.content.SharedPreferences


class RetroEaseLocalPreferences(context: Context) {

    var mSharedPreferences: SharedPreferences
    var mEditor: SharedPreferences.Editor

    init {
        mSharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
        mEditor.apply()
    }


    fun addInt(key: String, value: Int) {
        mEditor.putInt(key, value).apply()
    }


    fun getInt(key: String, def: Int): Int {
        return mSharedPreferences.getInt(key, def)!!
    }

    fun addString(key: String, value: String) {
        mEditor.putString(key, value).apply()
    }

    fun getString(key: String, def: String): String {
        return mSharedPreferences.getString(key, def)!!
    }

    fun addBool(key: String, value: Boolean) {
        mEditor.putBoolean(key, value).apply()
    }

    fun getBool(key: String, default: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, default)
    }//getBool ends

    fun deleteAllPreferences() {
        mEditor.clear().commit()
    }//deleteAllPreferences ends

    fun deletePreferenceWithKey(key: String) {
        mEditor.remove(key).commit()
    }
}