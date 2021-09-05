package com.app.iwitx.model

data class CityData(
    val csrf_tkn_name: String,
    val `data`: List<DataXX>,
    val message: String,
    val status: Int
)