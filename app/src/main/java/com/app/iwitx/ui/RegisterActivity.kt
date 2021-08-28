package com.app.iwitx.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.AuthListener
import com.app.iwitx.R
import com.app.iwitx.databinding.ActivityRegisterBinding
import com.app.iwitx.model.Response
import com.app.iwitx.model.UserData
import com.app.iwitx.viewmodel.AndroidViewModel

class RegisterActivity : AppCompatActivity() ,AuthListener {

    var dataModel : AndroidViewModel ? =null
    var activityRegisterBinding : ActivityRegisterBinding ?=null
    var otp_layout : LinearLayout ?=null
    var register_layout : LinearLayout ?=null
    var progressDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        activityRegisterBinding?.setLifecycleOwner(this);
        otp_layout = findViewById(R.id.otp_layout)
        register_layout = findViewById(R.id.register_layout)
        dataModel = ViewModelProvider(this).get(AndroidViewModel::class.java);
        activityRegisterBinding?.data = dataModel
        dataModel?.authListener = this

    }

    override fun onStarted() {
    }

    override fun onSuccess(response: String?) {

        Toast.makeText(this@RegisterActivity,""+response,Toast.LENGTH_SHORT).show()

    }



    override fun onFailure(message: String?) {
        Toast.makeText(this@RegisterActivity,""+message,Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessdata(response: Response?) {
      Toast.makeText(this@RegisterActivity,""+response?.message,Toast.LENGTH_SHORT).show()
        progressDialog?.setMessage("Please wait");
        progressDialog?.show()

        if(response?.message.equals("OTP Sent on Email.")!!)
        {
            SplashActivity.savePreferences("token",response?.csrf_tkn_name);
            Log.d("TAG","datamess"+response);
            progressDialog?.dismiss()
            register_layout?.visibility = View.GONE
            otp_layout?.visibility = View.VISIBLE
        }else{
            Log.d("TAG","datamessage"+response);
            register_layout?.visibility = View.VISIBLE
        }

    }

    override fun onloginsuccess(data: UserData?) {

    }

}