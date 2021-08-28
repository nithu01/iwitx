package com.app.iwitx

import com.app.iwitx.model.Response
import com.app.iwitx.model.UserData

interface AuthListener {

    fun onStarted()
    fun onSuccess(response: String?)
    fun onFailure(message:String?)
    fun onSuccessdata(message:Response?)
    fun onloginsuccess(dataa : UserData?)
}