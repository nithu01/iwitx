package com.app.iwitx.viewmodel
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.iwitx.AuthListener
import com.app.iwitx.config.SessionManager
import com.app.iwitx.config.config
import com.app.iwitx.model.Response
import com.app.iwitx.repository.Repository
import com.app.iwitx.ui.SplashActivity
import kotlinx.coroutines.launch


class AndroidViewModel : ViewModel() {

    var userid : String?=null
    var password : String ? =null
    var otp : String = ""
    var mobile : String=""
    var type : String=""
    var email : String=""
    var pass : String=""
    var authListener : AuthListener ?=null
    var gettokenresponse: MutableLiveData<Response> = MutableLiveData()
    var getloginesponse: MutableLiveData<Response> = MutableLiveData()
    var datarepository = Repository()
    var session: SessionManager? = null

    fun getdata(key: String, secret: String) : MutableLiveData<Response> {

        viewModelScope.launch {
            val response =  datarepository.gettoken(key, secret)
            gettokenresponse.value = response.body()

            SplashActivity.savePreferences("token", response.body()?.csrf_tkn_name);
        }
        return gettokenresponse
    }

    fun loggin(v: View)  {

        var token = SplashActivity.getPreferences("token", "");

        if(userid.isNullOrEmpty() || password.isNullOrEmpty()){

            authListener?.onFailure("Please fill credentials")

        }else{

            viewModelScope.launch {
                val response =  datarepository.login(token.toString(), config.key, config.secret, userid, password)
                SplashActivity.savePreferences("token", response.body()?.csrf_tkn_name);
                authListener?.onloginsuccess(response.body())

            }
        }
    }

    fun reggister(v: View) {

        var token = SplashActivity.getPreferences("token", "")

        if(email.isNullOrEmpty() || mobile.isNullOrEmpty() || type.isNullOrEmpty()){
            authListener?.onFailure("Please fill credentials")
        }

        viewModelScope.launch {
            val response =  datarepository.register(token.toString(), config.key, config.secret, mobile, type, email)
            getloginesponse.value = response.body()
            SplashActivity.savePreferences("token", response.body()?.csrf_tkn_name);
            Log.d("TAG", "REGISTER" + response.body()?.csrf_tkn_name);
            authListener?.onSuccessdata(response.body())
        }
   }

    fun veriftotp(v: View) {

        var token = SplashActivity.getPreferences("token", "")
        if(otp.isNullOrEmpty() || pass.isNullOrEmpty() ){
            authListener?.onFailure("Please fill credentials")
        }
       // Log.d("TAG","VERIFYOTP"+otp+""+pass+""+token);
        viewModelScope.launch {
            val response =  datarepository.verifyotp(token.toString(), config.key, config.secret, mobile, type, email, otp, pass)
            authListener?.onSuccess(response.code().toString())
        }

    }

    fun forgetpass(v: View) {

        var token = SplashActivity.getPreferences("token", "")
        if(otp.isNullOrEmpty() || pass.isNullOrEmpty() ){
            authListener?.onFailure("Please fill credentials")
        }
        viewModelScope.launch {
            val response =  datarepository.forgetpass(token.toString(), config.key, config.secret, mobile, type, email, otp, pass)
            authListener?.onSuccess(response.code().toString())
        }

    }

    fun profile(userid: String) {

        var token = SplashActivity.getPreferences("token", "")
        if(otp.isNullOrEmpty() || pass.isNullOrEmpty() ){
            authListener?.onFailure("Please fill credentials")
        }
        viewModelScope.launch {
            val response =  datarepository.profile(token.toString(), config.key, config.secret,userid)
            authListener?.onSuccess(response.code().toString())
        }

    }

}
