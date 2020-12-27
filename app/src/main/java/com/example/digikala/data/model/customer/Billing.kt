package com.example.digikala.data.model.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Billing(@SerializedName("first_name") @Expose  val first_name : String,
                   @SerializedName("last_name") @Expose  val last_name : String,
                   @SerializedName("company") @Expose  val company : String,
                   @SerializedName("address_1") @Expose  val address_1 : String,
                   @SerializedName("address_2") @Expose  val address_2 : String,
                   @SerializedName("city") @Expose  val city : String,
                   @SerializedName("state") @Expose  val state : String,
                   @SerializedName("postcode") @Expose  val postcode : String,
                   @SerializedName("country") @Expose  val country : String,
                   @SerializedName("email") @Expose  val email : String,
                   @SerializedName("phone") @Expose  val phone : String)
