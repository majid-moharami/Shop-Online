package com.example.digikala.data.model.customer

data class Customer(
        var email : String, var first_name: String, var last_name: String, var username : String,
        var billing : Billing, var shipping : Shipping)
