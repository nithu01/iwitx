package com.app.iwitx.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.iwitx.R
import com.app.iwitx.config.config
import com.app.iwitx.model.Data
import com.app.iwitx.viewmodel.AndroidViewModel
import com.google.android.material.navigation.NavigationView

class DashboardActivity  : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var dataModel : AndroidViewModel? =null
    var userId : String ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            var toolbar  = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            val navView: NavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)
            appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home,
                    R.id.nav_home,R.id.nav_gallery,R.id.nav_slideshow,R.id.nav_kyc), drawerLayout)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            init()
            profiledata()
    }

    fun profiledata(){
        dataModel?.profile(userId)?.observe(this@DashboardActivity, Observer { data->
                       var userdata = Data(
               data.data.id,
               data.data.userId,
               data.data.userType,
               data.data.kycStatus,
               data.data.addrStatus,
               data.data.bankStatus,
               data.data.status,
               data.data.mob,
               data.data.email,
               data.data.createDate,
               data.data.mainW,
               data.data.aepsW,
               data.data.matmW,
               data.data.commW,
               data.data.aadharImage,
               data.data.panImage,
               data.data.profileImage,
               data.data.chequeImage,
               data.data.aepsStatus,
               data.data.aepsEkycStatus
           )
           // Toast.makeText(this@DashboardActivity,""+data.data.userId,Toast.LENGTH_SHORT).show()
        })
    }

    fun init(){
        dataModel = ViewModelProvider(this).get(AndroidViewModel::class.java);
        userId =  SplashActivity.getPreferences("userId", "");
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}