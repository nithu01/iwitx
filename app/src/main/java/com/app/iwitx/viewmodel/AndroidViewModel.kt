package com.app.iwitx.viewmodel
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.iwitx.AuthListener
import com.app.iwitx.config.SessionManager
import com.app.iwitx.config.config
import com.app.iwitx.model.CityData
import com.app.iwitx.model.Response
import com.app.iwitx.model.StateData
import com.app.iwitx.model.UserData
import com.app.iwitx.repository.Repository
import com.app.iwitx.ui.SplashActivity
import kotlinx.coroutines.launch


class AndroidViewModel : ViewModel() {

    var userid : String?=null
    var password : String ? =null
    var otp : String = ""
    var mobile : String=""
    var type : String=""
    var selectedItemPosition : Int = 0
    var email : String=""
    var pass : String=""
    var name : String=""
    var dob : String=""
    var gender : String=""
    var aadhar : String=""
    var pan : String=""
    var vill : String=""
    var aadress : String=""
    var pincode : String=""

    var authListener : AuthListener ?=null
    var gettokenresponse: MutableLiveData<Response> = MutableLiveData()
    var getloginesponse: MutableLiveData<Response> = MutableLiveData()
    var profiledata: MutableLiveData<UserData> = MutableLiveData()
    var state: MutableLiveData<StateData> = MutableLiveData()
    var city: MutableLiveData<CityData> = MutableLiveData()
    var upload_data: MutableLiveData<Response> = MutableLiveData()
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

    fun getcity(stateid:String) : MutableLiveData<CityData>{

        var token = SplashActivity.getPreferences("token", "")

        viewModelScope.launch {
            val response =  datarepository.getcity( config.key, config.secret,token.toString(), stateid)
            city.value = response.body()
        }
        return city
    }

    fun uploadkyc(stateid:String,cityid:String) : MutableLiveData<Response>{

        var token = SplashActivity.getPreferences("token", "")

        viewModelScope.launch {
            val response =  datarepository.updatekyc( config.key, config.secret,token.toString(), stateid,name,dob,gender,aadhar,pan,stateid,cityid,vill,aadress,pincode)
            upload_data.value = response.body()
        }
        return upload_data
    }


    fun getstate() : MutableLiveData<StateData> {

        var token = SplashActivity.getPreferences("token", "")

        viewModelScope.launch {
            val response =  datarepository.getstate(token.toString(), config.key, config.secret)
            state.value = response.body()
        }
        return state
    }


    fun profile(userid: String?) : MutableLiveData<UserData>{

        var token = SplashActivity.getPreferences("token", "")
        if(otp.isNullOrEmpty() || pass.isNullOrEmpty() ){
            authListener?.onFailure("Please fill credentials")
        }
        viewModelScope.launch {
            val response =  datarepository.profile(token.toString(), config.key, config.secret,userid)
            profiledata.value = response.body()
        }
        return profiledata

    }

}
