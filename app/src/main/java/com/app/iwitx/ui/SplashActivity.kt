package com.app.iwitx.ui

import android.content.Intent
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.R
import com.app.iwitx.config.SessionManager
import com.app.iwitx.config.config
import com.app.iwitx.repository.Repository
import com.app.iwitx.viewmodel.AndroidViewModel

class SplashActivity : AppCompatActivity() {

    var androidViewModel = AndroidViewModel();
    var session: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        init()
        gettoken()
    }

    fun init(){
        androidViewModel = ViewModelProvider(this).get(AndroidViewModel::class.java);
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this@SplashActivity)
    }

    fun gettoken(){
        androidViewModel.getdata(config.key,config.secret).observe(this@SplashActivity, Observer {data->
            Log.d("TAG","TOKEN"+data.csrf_tkn_name)
            if(data.message=="Welcome to IWITX TECHNOLOGIES PRIVATE LIMITED."){
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            }
        })
    }

    companion object{
        var sharedpreferences: SharedPreferences? = null
        var editor: SharedPreferences.Editor? = null
        fun savePreferences(key: String?, value: String?) {
            editor = sharedpreferences?.edit()
            editor?.putString(key, value)
            editor?.apply()
        }
        fun getPreferences(key: String?, `val`: String?): String? {
            return sharedpreferences?.getString(key, `val`)
        }
    }

}