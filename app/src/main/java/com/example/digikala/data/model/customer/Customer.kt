package com.example.digikala.data.model.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Customer     //create a simple customer
(@field:Expose @field:SerializedName("email") var email: String, @field:Expose @field:SerializedName("first_name") var firstName: String, @field:Expose @field:SerializedName("last_name") var lastName: String, @field:Expose @field:SerializedName("shipping") var shipping: Shipping) : Serializable {
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

    @SerializedName("is_paying_customer")
    @Expose
    var isPayingCustomer: Boolean? = null

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    val fullName: String
        get() = "$firstName $lastName"

    companion object {
        private const val serialVersionUID = 1006749390067400703L
    }
}
