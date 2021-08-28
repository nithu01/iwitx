package com.app.iwitx.model

import com.google.gson.annotations.SerializedName

data class Data (

        val id : String,
        val userId : String,
        val userType : String,
        val kycStatus : String,
        val addrStatus : String,
        val bankStatus : String,
        val status : String,
        val name : String,
        val mob : String,
        val email : String,
        val createDate : String,
        val mainW : String,
        val aepsW : String,
        val matmW : String,
        val commW : String,
        val dob : String,
        val gender : String,
        val villCity : String,
        val address : String,
        val pincode : String,
        val aadharImage : String,
        val panImage : String,
        val profileImage : String,
        val chequeImage : String,
        val lastLogin : String,
        val aepsStatus : String,
        val aepsEkycStatus : String
)
