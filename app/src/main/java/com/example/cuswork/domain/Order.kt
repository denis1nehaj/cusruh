package com.example.cuswork.domain

data class Order(
    var width: Float? = null,
    var height: Float? = null,
    var length: Float? = null,
    var fitting: Fitting? = null,
    var material: Material? = null,
    var furniture: Furniture? = null,
    var price: Int? = null
)

