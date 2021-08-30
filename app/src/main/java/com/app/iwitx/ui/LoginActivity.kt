package com.app.iwitx.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.app.iwitx.config.Prefmanager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.AuthListener
import com.app.iwitx.R
import com.app.iwitx.config.SessionManager
import com.app.iwitx.databinding.ActivityLoginBinding
import com.app.iwitx.model.Data
import com.app.iwitx.model.Response
import com.app.iwitx.model.UserData
import com.app.iwitx.viewmodel.AndroidViewModel
import java.util.Calendar.getInstance

class LoginActivity : AppCompatActivity(), AuthListener {

    var dataModel : AndroidViewModel? =null
    var activityLoginBinding : ActivityLoginBinding ?=null
    var textView : TextView?=null
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

    override fun onSuccess(response:String?) {

    }

    /* ON LOGIN FAILURE*/
    override fun onFailure(message:String?) {
        Toast.makeText(this@LoginActivity, "Failure Data" + message, Toast.LENGTH_SHORT).show()

    }

    /* ON LOGIN SUCCESS*/
    override fun onSuccessdata(message: Response?) {

    }

    override fun onloginsuccess(data: UserData?) {

        Toast.makeText(this@LoginActivity, "" + data?.message, Toast.LENGTH_SHORT).show()


        if(data?.status == "1"){
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
            Toast.makeText(this@LoginActivity,""+userdata.id, Toast.LENGTH_SHORT).show()
            session!!.setLogin(true)
            Prefmanager.getInstance(this@LoginActivity).userLogin(userdata)
            startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))

        }else{
            Toast.makeText(this@LoginActivity, "" + data?.message, Toast.LENGTH_SHORT).show()
        }

    }


}