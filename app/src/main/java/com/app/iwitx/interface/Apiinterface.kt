package com.app.iwitx.`interface`

import com.app.iwitx.model.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Apiinterface {

    @GET("index")
    suspend fun generate_token(@Header("key")userid : String, @Header("secret")apicode : String):Response<com.app.iwitx.model.Response>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("csrf_tkn_name")token : String, @Field("key")key : String, @Field("secret")secret : String, @Field("userId")userId : String?, @Field("password")password : String?):Response<UserData>

    @FormUrlEncoded
    @POST("userRegistration")
    suspend fun register(@Field("csrf_tkn_name")token : String, @Field("key")key : String, @Field("secret")secret : String, @Field("mob")mobile : String, @Field("type")type : String, @Field("email")email : String):Response<com.app.iwitx.model.Response>

    @FormUrlEncoded
    @POST("verifyOtp")
    suspend fun verifyotp(@Field("csrf_tkn_name")token : String, @Field("key")key : String, @Field("secret")secret : String, @Field("mob")mobile : String, @Field("type")type : String, @Field("email")email : String, @Field("otp")otp : String, @Field("pass")pass : String):Response<com.app.iwitx.model.Response>

    @FormUrlEncoded
    @POST("forgotPassword")
    suspend fun forgetpas(@Field("csrf_tkn_name")token : String, @Field("key")key : String, @Field("secret")secret : String,@Field("email")email : String):Response<com.app.iwitx.model.Response>


}