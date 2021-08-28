package com.app.iwitx.config

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SessionManager(context: Context) {

    private val TAG: String = SessionManager::class.java.getSimpleName()

    var pref: SharedPreferences? = null

    var editor: SharedPreferences.Editor? = null
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    var _context: Context? = null

    var PRIVATE_MODE = 0

    private val PREF_NAME: String = "PREF_NAME"

    private val KEY_IS_LOGGEDIN = "isLoggedIn"
    init {
        pref = context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref?.edit()
    }

    fun setLogin(isLoggedIn: Boolean) {
        editor!!.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn)

        // commit changes
        editor!!.commit()
        Log.d(TAG, "User login session modified!")
    }

    fun isLoggedIn(): Boolean {
        return pref!!.getBoolean(KEY_IS_LOGGEDIN, false)
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref!!.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor!!.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor!!.commit()
    }
}