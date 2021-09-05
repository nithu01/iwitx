package com.app.iwitx.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.R
import com.app.iwitx.config.Prefmanager
import com.app.iwitx.config.SessionManager
import com.app.iwitx.config.config
import com.app.iwitx.viewmodel.AndroidViewModel

class SplashActivity : AppCompatActivity() {
    private val prefmanager: Prefmanager? = null
    var androidViewModel = AndroidViewModel();
    lateinit var session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        init()

        postdata()
    }

    fun postdata(){

        doWork()
        startApp()

    }

    private fun doWork() {
        var progress = 0
        while (progress < 100) {
            try {
                Thread.sleep(1000)
                //  progressBar.setProgress(progress)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            progress += 30
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private fun startApp() {
        if (session.isLoggedIn()) {

            val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            /*GET TOKEN*/
            androidViewModel.getdata(config.key, config.secret).observe(this@SplashActivity, Observer { data ->
                Log.d("TAG", "TOKEN" + data.csrf_tkn_name)
                if (data.message == "Welcome to IWITX TECHNOLOGIES PRIVATE LIMITED.") {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            })
        }
    }
    fun init(){
        session = SessionManager(this@SplashActivity)
        androidViewModel = ViewModelProvider(this).get(AndroidViewModel::class.java);
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this@SplashActivity)
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