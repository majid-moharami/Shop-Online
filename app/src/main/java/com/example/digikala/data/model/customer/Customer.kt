package com.example.digikala.data.model.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Customer : Serializable {
    constructor(email : String){
        mEmail = email
    }
    constructor(email: String , fistName : String , lastName : String , shipping : Shipping){
        mEmail = email
        mFirstName = fistName
        mLastName = lastName
        mShipping = shipping
    }

    @SerializedName("email")
    @Expose
    var mEmail : String? = null
    @SerializedName("first_name")
    @Expose
    var mFirstName : String? = null
    @SerializedName("last_name")
    @Expose
    var mLastName : String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("role")
    @Expose
    var role = "simpleCustomer"

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("billing")
    @Expose
    var billing: Billing? = null
    @SerializedName("shilling")
    @Expose
    var mShipping: Shipping? = null

    @SerializedName("is_paying_customer")
    @Expose
    var isPayingCustomer: Boolean? = null

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    val fullName: String?
        get() = "$mFirstName $mLastName"

    companion object {
        private const val serialVersionUID = 1006749390067400703L
    }
}
