package com.app.iwitx.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.AuthListener
import com.app.iwitx.R
import com.app.iwitx.config.SessionManager
import com.app.iwitx.config.config
import com.app.iwitx.databinding.ActivityLoginBinding
import com.app.iwitx.model.Data
import com.app.iwitx.model.Response
import com.app.iwitx.model.UserData
import com.app.iwitx.viewmodel.AndroidViewModel

class LoginActivity : AppCompatActivity(), AuthListener {

    var dataModel : AndroidViewModel? =null
    var activityLoginBinding : ActivityLoginBinding ?=null
    var textView : TextView?=null
    var androidViewModel = AndroidViewModel();
    var session: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        textView?.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    fun init(){
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityLoginBinding?.setLifecycleOwner(this);
        dataModel = ViewModelProvider(this).get(AndroidViewModel::class.java);
        session = SessionManager(this@LoginActivity)
        activityLoginBinding?.data = dataModel
        dataModel?.authListener = this

        textView = findViewById(R.id.signup)
    }
    override fun onStarted() {

    }

    override fun onSuccess(response: String?) {

    }

    override fun onFailure(message: String?) {
        Toast.makeText(this@LoginActivity, "Failure Data" + message, Toast.LENGTH_SHORT).show()

    }

    override fun onSuccessdata(message: Response?) {

    }
//
//    override fun onSuccessdata(message: String?) {
//        Toast.makeText(this@LoginActivity,"success data"+message, Toast.LENGTH_SHORT).show()
//

    override fun onloginsuccess(data: UserData?) {
//    }
        Toast.makeText(this@LoginActivity, "" + data?.message, Toast.LENGTH_SHORT).show()

        // Toast.makeText(this@LoginActivity,""+data?.status+"\n"+data?.message, Toast.LENGTH_SHORT).show()
        if(data?.status==1){
           var userdata = Data(data.data.id,
                   data.data.userId,
                   data.data.userType,
                   data.data.addrStatus,
                   data.data.kycStatus,

                   data.data.bankStatus,
                   data.data.status,
                   data.data.name,
                   data.data.mob,
                   data.data.email,
                   data.data.createDate,
                   data.data.mainW,
                   data.data.aepsW,
           data.data.matmW,
           data.data.commW,
           data.data.dob,
           data.data.gender,
                   data.data.villCity,
                   data.data.address,
           data.data.pincode,
           data.data.aadharImage,
           data.data.panImage,
           data.data.profileImage,
           data.data.chequeImage,
           data.data.lastLogin,
           data.data.aepsStatus,
           data.data.aepsEkycStatus)
            session!!.setLogin(true)
            androidViewModel.getdata(config.key, config.secret).observe(
                this@LoginActivity,
                Observer { data ->

                })
        }else{
            Toast.makeText(this@LoginActivity, "" + data?.message, Toast.LENGTH_SHORT).show()
        }

    }


}