package com.example.digikala.data.model.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Shipping     //create shipping info
(@field:Expose @field:SerializedName("first_name") var firstName: String, @field:Expose @field:SerializedName("last_name") var lastName: String, @field:Expose @field:SerializedName("address_1") var address1: String, @field:Expose @field:SerializedName("postcode") var postcode: String, @field:Expose @field:SerializedName("city") var city: String, @field:Expose @field:SerializedName("state") var state: String, @field:Expose @field:SerializedName("country") var country: String) : Serializable {

    @SerializedName("company")
    @Expose
    var company: String? = null

    @SerializedName("address_2")
    @Expose
    var address2: String? = null

    companion object {
        private const val serialVersionUID = -6075387147230539384L
    }
}
