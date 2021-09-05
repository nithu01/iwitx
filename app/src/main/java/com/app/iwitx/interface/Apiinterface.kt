package com.app.iwitx.`interface`

import com.app.iwitx.model.CityData
import com.app.iwitx.model.StateData
import com.app.iwitx.model.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Apiinterface {

    @FormUrlEncoded
    @POST("/externalUse/api/getDistrictListByStateId")
    suspend fun district(@Field("key")userid : String, @Field("secret")apicode : String, @Field("csrf_tkn_name")userdId : String,@Field("stateId")stateId : String):Response<CityData>

    @FormUrlEncoded
    @POST("/externalUse/api/stateList")
    suspend fun state(@Field("key")key : String, @Field("secret")secret : String, @Field("csrf_tkn_name")csrf_tkn_name : String):Response<StateData>

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

    @FormUrlEncoded
    @POST("profile")
    suspend fun profile(@Field("csrf_tkn_name")token : String, @Field("key")key : String, @Field("secret")secret : String,@Field("userId")userId : String?):Response<UserData>

    @FormUrlEncoded
    @POST("/externalUse/api/uploadKycData")
    suspend fun updatekyc(@Field("csrf_tkn_name")token : String, @Field("key")key : String, @Field("secret")secret : String,@Field("userId")userId : String?,@Field("name")name : String?,@Field("dob")dob : String?,@Field("gender")gender : String?,@Field("aadhar")aadhar : String?,@Field("pan")pan : String?,@Field("stateId")stateId : String?,@Field("districtId")districtId : String?,@Field("villCity")villCity : String?,@Field("address")address : String?,@Field("pincode")pincode : String?):Response<com.app.iwitx.model.Response>

}