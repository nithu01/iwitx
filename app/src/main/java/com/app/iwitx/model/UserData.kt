package com.app.iwitx.model

import com.google.gson.annotations.SerializedName

data class UserData (val status : Int,val data : Data, val message : String,val csrf_tkn_name : String)
