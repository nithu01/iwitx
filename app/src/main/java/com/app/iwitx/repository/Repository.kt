package com.app.iwitx.repository
import android.util.Log
import retrofit2.Response
import com.app.iwitx.config.ApiClient
import com.app.iwitx.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.crypto.SecretKey

class Repository  {

    suspend fun gettoken(uid : String,secretKey : String): Response<com.app.iwitx.model.Response> {
        return withContext(Dispatchers.IO){
            ApiClient().getApiService().generate_token(uid,secretKey);
        }
    }
    suspend fun login(token: String, key: String, secret: String, userId: String?, password: String?): Response<UserData> {
        return withContext(Dispatchers.IO){
            ApiClient().getApiService().login(token,key,secret,userId,password);
        }
    }

    suspend fun register(token : String,key : String,secret : String,mobile : String,type : String,email : String): Response<com.app.iwitx.model.Response> {
        return withContext(Dispatchers.IO){
            ApiClient().getApiService().register(token,key,secret,mobile,type,email);
        }
    }

    suspend fun verifyotp(token : String,key : String,secret : String,mobile : String,type : String,email : String,otp : String,pass : String): Response<com.app.iwitx.model.Response> {
        return withContext(Dispatchers.IO){
            ApiClient().getApiService().verifyotp(token,key,secret,mobile,type,email,otp,pass);
        }
    }

    suspend fun forgetpass(token : String,key : String,secret : String,mobile : String,type : String,email : String,otp : String,pass : String): Response<com.app.iwitx.model.Response> {
        return withContext(Dispatchers.IO){
            ApiClient().getApiService().verifyotp(token,key,secret,mobile,type,email,otp,pass);
        }
    }

    suspend fun profile(token : String,key : String,secret : String,userId: String): Response<UserData> {
        return withContext(Dispatchers.IO){
            ApiClient().getApiService().profile(token,key,secret,userId);
        }
    }


}