package com.app.iwitx.model

data class StateData(
    val csrf_tkn_name: String,
    val `data`: List<DataX>,
    val message: String,
    val status: Int
)