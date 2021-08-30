package com.app.iwitx.config

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.app.iwitx.model.Data

 class Prefmanager(context: Context){
    val PREFS_NAME = "iwitx"
    private val SHARED_PREF_NAME = PREFS_NAME
    private val TAG_TOKEN = "tagtoken"



    private var mCtx: Context? = null
    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val KEY_ID = "id"
    private val KEY_EMAIL = "email"
    private val KEY_USERID = "UserId"
    private val KEY_ADDR_STATUS = "address"
    private val KEY_KYC_STATUS = "kycstatus"
    private val KEY_BANK_STATUS = "bankstatus"
    private val KEY_STATUS = "status"
    private val KEY_MOBILE = "mobile"
    private val KEY_CREATED_DATE = "createddate"
    private val KEY_MAINW = "main"
    private val KEY_AEPSW = "aeps"
    private val KEY_MATMW = "matm"
    private val KEY_COMMW = "commission"
    private val KEY_ADHARIMAGE = "addharimage"
    private val KEY_PANIMAGE = "panimage"
    private val KEY_PROFILEIMAGE = "profileimage"
    private val KEY_CHEQUEIMAGE = "chequeimage"
    private val KEY_AEPSSTATUS = "aepsstatus"
    private val KEY_AEPSKYCSTATUS = "aepskycstatus"
    private val KEY_USERTYPE = "usertype"

    init {
        mCtx = context
        pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = pref?.edit()
    }

    companion object{
        lateinit var mInstance: Prefmanager
        @Synchronized
        fun getInstance(context: Context): Prefmanager {
            if (mInstance == null) {
                mInstance = Prefmanager(context)
            }
            return mInstance
        }
    }


    fun userLogin(userData: Data) {
        val sharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ID, userData.id)
        editor.putString(KEY_USERTYPE, userData.userType)
        editor.putString(KEY_KYC_STATUS, userData.kycStatus)
        editor.putString(KEY_ADDR_STATUS, userData.addrStatus)
        editor.putString(KEY_BANK_STATUS, userData.bankStatus)
        editor.putString(KEY_STATUS, userData.status)
        editor.putString(KEY_MOBILE, userData.mob)
        editor.putString(KEY_EMAIL, userData.email)
        editor.putString(KEY_CREATED_DATE, userData.createDate)
        editor.putString(KEY_MAINW, userData.mainW)
        editor.putString(KEY_AEPSW, userData.aepsW)
        editor.putString(KEY_MATMW, userData.mainW)
        editor.putString(KEY_COMMW, userData.commW)
        editor.putString(KEY_ADHARIMAGE, userData.aadharImage)
        editor.putString(KEY_PANIMAGE, userData.panImage)
        editor.putString(KEY_PROFILEIMAGE, userData.profileImage)
        editor.putString(KEY_CHEQUEIMAGE, userData.chequeImage)
        editor.putString(KEY_AEPSSTATUS, userData.aepsStatus)
        editor.putString(KEY_AEPSKYCSTATUS, userData.aepsEkycStatus)
        editor.apply()
    }

    fun getUserData(): Data? {
        val sharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return Data(
            sharedPreferences.getString(KEY_ID, null)!!,
            sharedPreferences.getString(KEY_USERID, null)!!,
            sharedPreferences.getString(KEY_USERTYPE, null)!!,
            sharedPreferences.getString(KEY_KYC_STATUS, null)!!,
            sharedPreferences.getString(KEY_ADDR_STATUS, null)!!,
            sharedPreferences.getString(KEY_BANK_STATUS, null)!!,
            sharedPreferences.getString(KEY_STATUS, null)!!,
            sharedPreferences.getString(KEY_MOBILE, null)!!,
            sharedPreferences.getString(KEY_EMAIL, null)!!,
            sharedPreferences.getString(KEY_CREATED_DATE, null)!!,
            sharedPreferences.getString(KEY_MAINW, null)!!,
            sharedPreferences.getString(KEY_AEPSW, null)!!,
            sharedPreferences.getString(KEY_MATMW, null)!!,
            sharedPreferences.getString(KEY_COMMW, null)!!,
            sharedPreferences.getString(KEY_ADHARIMAGE, null)!!,
            sharedPreferences.getString(KEY_PANIMAGE, null)!!,
            sharedPreferences.getString(KEY_PROFILEIMAGE, null)!!,
            sharedPreferences.getString(KEY_CHEQUEIMAGE, null)!!,
            sharedPreferences.getString(KEY_AEPSSTATUS, null)!!,
            sharedPreferences.getString(KEY_AEPSKYCSTATUS, null)!!
        )
    }

    fun logout() {
        val sharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        // mCtx.startActivity(new Intent(mCtx, SplashMainActivity.class));
    }
}