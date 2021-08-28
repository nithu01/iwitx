package com.app.iwitx.config

import com.app.iwitx.`interface`.Apiinterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    val base_url :String="https://www.iwitx.in/externalUse/api/";

    fun getApiService() : Apiinterface {
        return getClient()?.create(Apiinterface::class.java)!!
    }

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}