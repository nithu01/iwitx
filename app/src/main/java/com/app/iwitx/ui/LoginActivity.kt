package com.app.iwitx.ui
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.AuthListener
import com.app.iwitx.R
import com.app.iwitx.config.Prefmanager
import com.app.iwitx.config.SessionManager
import com.app.iwitx.databinding.ActivityLoginBinding
import com.app.iwitx.model.Data
import com.app.iwitx.model.Response
import com.app.iwitx.model.UserData
import com.app.iwitx.viewmodel.AndroidViewModel

class LoginActivity : AppCompatActivity(), AuthListener,View.OnClickListener {

    var dataModel : AndroidViewModel? =null
    var activityLoginBinding : ActivityLoginBinding ?=null
    var textView : TextView?=null
    var session: SessionManager? = null
    var imageview: ImageView?= null
    private var showpass = false
    var editText: EditText? = null

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
        imageview = findViewById(R.id.img_showpass)
        textView = findViewById(R.id.signup)
        imageview?.setOnClickListener(this)
        editText = findViewById(R.id.pass)
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
        Toast.makeText(this@LoginActivity,""+data?.data.toString(), Toast.LENGTH_SHORT).show()

        if(data?.message == "Successful Login."){
            session!!.setLogin(true)
           var userdata = Data(
               data.data.id,
               data.data.userId
           )
            Prefmanager.getInstance(this@LoginActivity).userLogin(userdata)
            startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
        }else{
            Toast.makeText(this@LoginActivity, "" + data?.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View?) {
        if (v === imageview) {
            val start: Int
            val end: Int
            if (showpass) {
                start = editText!!.getSelectionStart()
                end = editText!!.getSelectionEnd()
                editText!!.setTransformationMethod(PasswordTransformationMethod())
                editText!!.setSelection(start, end)
                showpass = false
                imageview!!.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                start = editText!!.getSelectionStart()
                end = editText!!.getSelectionEnd()
                editText!!.setTransformationMethod(null)
                editText!!.setSelection(start, end)
                showpass = true
                imageview!!.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
        }

    }


}